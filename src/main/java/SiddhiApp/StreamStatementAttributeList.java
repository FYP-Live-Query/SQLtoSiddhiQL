package SiddhiApp;

import java.util.ArrayList;
import java.util.List;

public class StreamStatementAttributeList implements IAttributeList, ISiddhiAppComposite{

    private final List<ISiddhiAppComposite> attributeDataTypePairs;

    public StreamStatementAttributeList() {
        this.attributeDataTypePairs = new ArrayList<>(10);
    }

    public List<ISiddhiAppComposite> getAttributeSetAndDataTypes() {
        return attributeDataTypePairs;
    }

<<<<<<< Updated upstream
    public void addAttribute(ISiddhiAppComposite attribute){
        attributeDataTypePairs.add(attribute);
    }
=======
    public void addAttribute(ISiddhiAppComposite attributeWithDataType){
        // TODO : create a hashmap and avoid O(n) comparisons

        for (ISiddhiAppComposite iSiddhiAppComposite : attributeListWithoutAliasesWithDataType) {

            if (iSiddhiAppComposite.equals(attributeWithDataType)) {
                return;
            }
>>>>>>> Stashed changes

    public String toString(){
        StringBuilder attributeSetWithDataType = new StringBuilder("");
        for(ISiddhiAppComposite attributeDataTypePair : attributeDataTypePairs){
            attributeSetWithDataType.append(attributeDataTypePair.getSiddhiAppCompositeAsString()).append(",");
        }
        return attributeSetWithDataType.toString();
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return toString();
    }
}
