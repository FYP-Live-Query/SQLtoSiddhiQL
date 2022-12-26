package SiddhiApp;

import SiddhiApp.Annotation.App.App;
import SiddhiApp.Annotation.Attributes.IAttributes;
import SiddhiApp.Annotation.Common.KeyValue;
import SiddhiApp.Annotation.Info.IInfo;
import SiddhiApp.Annotation.Map.IMap;
import SiddhiApp.Annotation.Sink.ISink;
import SiddhiApp.Annotation.Source.ISource;
import SiddhiApp.Statement.FilterExpressionStatement.FilterExpression;
import SiddhiApp.Statement.FilterExpressionStatement.IFilterExpression;
import SiddhiApp.Statement.From.FromStatement;
import SiddhiApp.Statement.From.IFromStatement;
import SiddhiApp.Statement.Insert.IInsertStatement;
import SiddhiApp.Statement.Insert.InsertStatement;
import SiddhiApp.Statement.Select.SelectStatement;

public class SiddhiApp {
    private final DefineStreamStatement defineStreamStatement = new DefineStreamStatement(); // create define input stream
    private final DefineStreamStatement defineOutputStreamStatement = new DefineStreamStatement(); // create define output stream
    private final SelectStatement selectStatement = new SelectStatement(); // select statement
    private final IFromStatement fromStatement = new FromStatement(); // from statement
    private final IFilterExpression filterExpression = new FilterExpression(); // filter statement
    private final IInsertStatement insertStatement = new InsertStatement(); // insert into statement
    private final App annotationApp; // Annotation @app
    private final ISource annotationSource; // Annotation @source
    private final IAttributes annotationAttributes; // Annotation @attributes
    private final IMap annotationMap; // Annotation @map
    private final ISink annotationSink; // Annotation @sink
    private final IInfo annotationInfo; // Annotation @info
    private String inputOutputStreamNamePrefix = null;
    private final StringBuilder stringSiddhiApp = new StringBuilder("");

    private SiddhiApp(SiddhiAppBuilder siddhiAppBuilder) {
        this.annotationSource = siddhiAppBuilder.annotationSource;
        this.annotationAttributes = siddhiAppBuilder.annotationAttributes;
        this.annotationMap = siddhiAppBuilder.annotationMap;
        this.annotationSink = siddhiAppBuilder.annotationSink;
        this.annotationInfo = siddhiAppBuilder.annotationInfo;
        this.annotationApp = siddhiAppBuilder.annotationApp;
    }

    public void addSymbolToFilterExpression(String symbol){
        ((FilterExpression) this.filterExpression).addSymbol(symbol);
    }

    public void addSelectItem(ISiddhiAppComposite selectItem){
        selectStatement.addSelectItem(selectItem);
    }

    public void addColumnWithDataType(ISiddhiAppComposite columnWithDataType){
        defineStreamStatement.addAttributeWithDataType(columnWithDataType);
        String columnName = ((ColumnWithDataType) columnWithDataType).getColumnName();
        String columnAlias = ((ColumnWithDataType) columnWithDataType).getAlias();
        if(columnAlias == null) {
            defineOutputStreamStatement.addAttributeWithDataType(columnWithDataType);
        }else{
            ColumnWithDataType columnWithDataTypeWithAlias = new ColumnWithDataType(
                    new Column(columnAlias,null),((ColumnWithDataType) columnWithDataType).getDataType()
            );
            defineOutputStreamStatement.addAttributeWithDataType(columnWithDataTypeWithAlias);
        }
        annotationAttributes.addAttributeComposite(new KeyValue<>(columnName,columnName)); // add to attribute annotation
    }

    public void setStreamNamePrefix(String inputOutputStreamNamePrefix) {
        this.inputOutputStreamNamePrefix = inputOutputStreamNamePrefix;
    }

    public String getSiddhiAppStringRepresentation(){
        // Annotations
            // app name
        stringSiddhiApp.append(annotationApp.getSiddhiAppCompositeAsString());
            // source
         annotationMap.addMapComposite(annotationAttributes);
        annotationSource.addSourceComposite(annotationMap);
        stringSiddhiApp.append(annotationSource.getSiddhiAppCompositeAsString());
        defineStreamStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream"); // IPStream set I/P Stream Name
        stringSiddhiApp.append(defineStreamStatement.getSiddhiAppCompositeAsString());

        // Annotations
            // sink
        stringSiddhiApp.append(annotationSink.getSiddhiAppCompositeAsString());

        defineOutputStreamStatement.setStreamName(inputOutputStreamNamePrefix + "OutputStream"); // OPStream set O/P Stream Name
        stringSiddhiApp.append(defineOutputStreamStatement.getSiddhiAppCompositeAsString());
        // Annotations
            // info
        stringSiddhiApp.append(annotationInfo.getSiddhiAppCompositeAsString());

        fromStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream"); // From Statement set I/P Stream Name
        fromStatement.setFromStatementComposite(filterExpression); // From statement filter expression
        stringSiddhiApp.append(fromStatement.getSiddhiAppCompositeAsString());

        stringSiddhiApp.append(selectStatement.getSiddhiAppCompositeAsString()); // select statement

        insertStatement.setOutputStreamName(inputOutputStreamNamePrefix + "OutputStream"); // insert statement set O/P Stream name
        stringSiddhiApp.append(insertStatement.getSiddhiAppCompositeAsString());
        return stringSiddhiApp.toString();
    }

    // Bloch’s Builder pattern
    public static class SiddhiAppBuilder{
        private ISource annotationSource;
        private IAttributes annotationAttributes;
        private IMap annotationMap;
        private ISink annotationSink;
        private IInfo annotationInfo;
        private final App annotationApp;

        public SiddhiAppBuilder(String siddhiAppName) {
            annotationApp = new App();
            annotationApp.setSiddhiApplicationName(siddhiAppName);
        }

        public SiddhiAppBuilder setAnnotationSource(ISource annotationSource) {
            this.annotationSource = annotationSource;
            return this;
        }

        public SiddhiAppBuilder setAnnotationAttributes(IAttributes annotationAttributes) {
            this.annotationAttributes = annotationAttributes;
            return this;
        }

        public SiddhiAppBuilder setAnnotationMap(IMap annotationMap) {
            this.annotationMap = annotationMap;
            return this;
        }

        public SiddhiAppBuilder setAnnotationSink(ISink annotationSink) {
            this.annotationSink = annotationSink;
            return this;
        }

        public SiddhiAppBuilder setAnnotationInfo(IInfo annotationInfo) {
            this.annotationInfo = annotationInfo;
            return this;
        }

        public SiddhiApp build(){
            return new SiddhiApp(this);
        }

    }
}