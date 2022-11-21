package Engine;

import SiddhiApp.AggregateFunction;
import SiddhiApp.ColumnWIthDataType;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

import java.util.Stack;

public class WhereExpressionHandlingBehavior extends IExpressionHandleBehavior{

    private SiddhiApp.Column siddhiColumn;
    private Stack<AggregateFunction> aggregateFunctionsStack = new Stack<>(); // to handle functions inside functions
    private AggregateFunction aggregateFunction;

    public WhereExpressionHandlingBehavior() {
    }

    @Override
    public void handleTable(Table table) {

    }

    @Override
    public void handleColumn(Column sqlColumn) {
        siddhiColumn = new SiddhiApp.Column();
        siddhiColumn.setName(sqlColumn.getName(false));
        // if still processing on function attributes add to function attribute list
        if(aggregateFunctionsStack.empty()) {
            siddhiApp.addColumnWithDataType(
                    new ColumnWIthDataType(siddhiColumn, "String")); // add to stream definition
        }else{
            aggregateFunctionsStack.peek().addAttribute(siddhiColumn); // add to function
            siddhiApp.addColumnWithDataType(
                    new ColumnWIthDataType(siddhiColumn, aggregateFunctionsStack.peek().getFunctionAttributeDataType())); // add to stream definition
        }
    }

    @Override
    public void handleFunctionExit(Function function) {

    }

    @Override
    public void handleFunctionBegin(Function function) {

    }


    @Override
    public void handleSignedExpression(SignedExpression signedExpression) {

    }

    @Override
    public void handleDoubleValue(DoubleValue doubleValue) {

    }

    @Override
    public void handleLongValue(LongValue longValue) {

    }

    @Override
    public void handleParenthesis(Parenthesis parenthesis) {

    }

    @Override
    public void handleStringValue(StringValue stringValue) {

    }

    @Override
    public void handleAddition(Addition addition) {

    }

    @Override
    public void handleDivision(Division division) {

    }

    @Override
    public void handleIntegerDivision(IntegerDivision integerDivision) {

    }

    @Override
    public void handleMultiplication(Multiplication multiplication) {

    }

    @Override
    public void handleSubtraction(Subtraction subtraction) {

    }

    @Override
    public void handleAndExpression(AndExpression andExpression) {

    }

    @Override
    public void handleOrExpression(OrExpression orExpression) {

    }

    @Override
    public void handleXorExpression(XorExpression xorExpression) {

    }

    @Override
    public void handleOpenBracket() {

    }

    @Override
    public void handleCloseBracket() {

    }

    @Override
    public void handleEqualsTo(EqualsTo equalsTo) {

    }

    @Override
    public void handleGreaterThan(GreaterThan greaterThan) {

    }

    @Override
    public void handleGreaterThanEquals(GreaterThanEquals greaterThanEquals) {

    }

    @Override
    public void handleMinorThan(MinorThan minorThan) {

    }

    @Override
    public void handleMinorThanEquals(MinorThanEquals minorThanEquals) {

    }

    @Override
    public void handleNotEqualsTo(NotEqualsTo notEqualsTo) {

    }

    @Override
    public void handleAlias(Alias alias) {
        throw new UnsupportedOperationException("Alias in where statement is not supported");
    }
}
