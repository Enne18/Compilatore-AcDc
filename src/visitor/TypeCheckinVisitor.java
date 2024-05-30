package visitor;

import java.util.ArrayList;
import java.util.Iterator;

import ast.LangType;
import ast.NodeAssing;
import ast.NodeBinOp;
import ast.NodeConvert;
import ast.NodeCost;
import ast.NodeDecSt;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeExpr;
import ast.NodeId;
import ast.NodePrint;
import ast.NodeProgram;
import ast.TipoTD;
import ast.TypeDescriptor;
import symbolTable.SymbolTable;
import symbolTable.SymbolTable.Attributes;

/**
 * Classe che implementa un visitor per l'analisi semantica e il controllo dei tipi
 */
public class TypeCheckinVisitor implements IVisitor {

    private TypeDescriptor resType;
    private ArrayList<TypeDescriptor> riga;
    private int currentLine;

    public TypeDescriptor getResType() {
        return resType;
    }

    public void setResType(TypeDescriptor resType) {
        this.resType = resType;
    }

    public Iterator<TypeDescriptor> getRiga() {
        return riga.iterator();
    }

    public TypeCheckinVisitor() {
        SymbolTable.init();
        riga = new ArrayList<>();
        currentLine = 0;
    }

    @Override
    public void visit(NodeProgram node) {
        for (NodeDecSt lista : node.getValue()) {
        	currentLine++;
            lista.accept(this);
        }
    }

    @Override
    public void visit(NodeId node) {
        String id = node.getValue();
        Attributes att = SymbolTable.lookup(id);
        
        if (att == null) {
            resType = new TypeDescriptor(TipoTD.ERROR, id + " non dichiarato", currentLine);
        } else if (att.getTipo() == LangType.INTEGER) {
            resType = new TypeDescriptor(TipoTD.INT);
        } else {
            resType = new TypeDescriptor(TipoTD.FLOAT);
        }
    }

    @Override
    public void visit(NodeCost node) {
        LangType type = node.getValueType();
        
        if (type == LangType.INTEGER) {
            resType = new TypeDescriptor(TipoTD.INT);
        } else {
            resType = new TypeDescriptor(TipoTD.FLOAT);
        }
    }

    @Override
    public void visit(NodeConvert node) {
        resType = new TypeDescriptor(TipoTD.FLOAT);
    }

    @Override
    public void visit(NodePrint node) {
        node.getValue().accept(this);
        
        if (resType.getTipo() != TipoTD.ERROR) {
            this.resType = new TypeDescriptor(TipoTD.OK);
        }
    }

    @Override
    public void visit(NodeDeref node) {
        node.getValueId().accept(this);
    }

    @Override
    public void visit(NodeBinOp node) {
        NodeExpr left = node.getValueLeft();
        left.accept(this);
        TypeDescriptor leftTD = resType;
        
        NodeExpr right = node.getValueRight();
        right.accept(this);
        TypeDescriptor rightTD = resType;
        
        if (leftTD.getTipo() == TipoTD.ERROR) {
            resType = leftTD;
            return;
        }
        if (rightTD.getTipo() == TipoTD.ERROR) {
            resType = rightTD;
            return;
        }
        
        if (leftTD.getTipo() != rightTD.getTipo()) {
            if (leftTD.getTipo() == TipoTD.INT) {
                node.setLeft(new NodeConvert(node.getValueLeft()));
            } else {
                node.setRight(new NodeConvert(node.getValueRight()));
            }
            resType = new TypeDescriptor(TipoTD.FLOAT);
        } else {
            if (leftTD.getTipo() == TipoTD.INT) {
                resType = new TypeDescriptor(TipoTD.INT);
            } else {
                resType = new TypeDescriptor(TipoTD.FLOAT);
            }
        }
    }

    @Override
    public void visit(NodeDecl node) {
        NodeId id = node.getValueId();
        LangType type = node.getValueType();
        NodeExpr init = node.getValueExpr();

        if (SymbolTable.lookup(id.getValue()) != null) {
            resType = new TypeDescriptor(TipoTD.ERROR, "ERRORE - Variabile già dichiarata.", currentLine);
        } else {
            if (init != null) {
                init.accept(this);
            }
            Attributes attributi = new Attributes(type);
            SymbolTable.enter(id.getValue(), attributi);
            id.setAttributo(attributi);
            resType = new TypeDescriptor(TipoTD.OK);
        }
    }

    @Override
    public void visit(NodeAssing node) {
        NodeId id = node.getValueId();
        NodeExpr expr = node.getValueExpr();
        id.accept(this);
        TypeDescriptor idTD = resType;
        expr.accept(this);
        TypeDescriptor exprTD = resType;
        
        if (idTD.getTipo() == TipoTD.ERROR) {
            resType = exprTD;
            return;
        }
        if (exprTD.getTipo() == TipoTD.ERROR) {
            resType = exprTD;
            return;
        }
        
        if (idTD.getTipo() == TipoTD.INT && exprTD.getTipo() == TipoTD.FLOAT) {
            resType = new TypeDescriptor(TipoTD.ERROR, "Impossibile assegnare valore FLOAT ad ID INT", currentLine);
            return;
        }
        resType = new TypeDescriptor(TipoTD.OK);
    }
}
