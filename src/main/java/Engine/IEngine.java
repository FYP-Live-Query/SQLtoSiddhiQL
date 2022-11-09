package Engine;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

public abstract class IEngine {
    protected IExpressionHandleBehavior engineBehavior;

    public void setExpressionHandlingBehavior(IExpressionHandleBehavior engineBehavior){
        this.engineBehavior = engineBehavior;
    }

    public abstract void handleTable(Table table);

    public abstract void handleColumn(Column columnName);

    public abstract void handleFunction(Function function);

    public abstract void handleSignedExpression(SignedExpression signedExpression);

    public abstract void handleDoubleValue(DoubleValue doubleValue);

    public abstract void handleLongValue(LongValue longValue);

    public abstract void handleParenthesis(Parenthesis parenthesis);

    public abstract void handleStringValue(StringValue stringValue);

    public abstract void handleAddition(Addition addition);

    public abstract void handleDivision(Division division);

    public abstract void handleIntegerDivision(IntegerDivision integerDivision);

    public abstract void handleMultiplication(Multiplication multiplication);

    public abstract void handleSubtraction(Subtraction subtraction);

    public abstract void handleAndExpression(AndExpression andExpression);

    public abstract void handleOrExpression(OrExpression orExpression);

    public abstract void handleXorExpression(XorExpression xorExpression);

    public abstract void handleEqualsTo(EqualsTo equalsTo);

    public abstract void handleGreaterThan(GreaterThan greaterThan);

    public abstract void handleGreaterThanEquals(GreaterThanEquals greaterThanEquals);

    public abstract void handleMinorThan(MinorThan minorThan);

    public abstract void handleMinorThanEquals(MinorThanEquals minorThanEquals);

    public abstract void handleNotEqualsTo(NotEqualsTo notEqualsTo);

    public abstract void handleAlias(Alias alias);
}