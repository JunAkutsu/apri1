package com.apri.common.customTag;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

public class LabelAttributeTagProcessor extends AbstractAttributeTagProcessor {

    private static final String ATTR_NAME = "label";
    private static final int PRECEDENCE = 10000;

    public LabelAttributeTagProcessor(final String dialectPrefix) {
        super(
            TemplateMode.HTML, // This processor will apply only to HTML mode
            dialectPrefix,     // Prefix to be applied to name for matching
            null,              // No tag name: match any tag name
            false,             // No prefix to be applied to tag name
            ATTR_NAME,         // Name of the attribute that will be matched
            true,              // Apply dialect prefix to attribute name
            PRECEDENCE,        // Precedence (inside dialect's precedence)
            true);             // Remove the matched attribute afterwards
    }


    protected void doProcess(
            final ITemplateContext context, final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {

    	// 初期化
    	Object label = null;
    	
    	// コレクションの取得
        final IEngineConfiguration configuration = context.getConfiguration();
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(context, attributeValue);
        final Object collection = (Object) expression.execute(context);
        
        // 値の取得
        String selectedValue = (String)tag.getAttributeValue("selectedValue");
        final IStandardExpression expression2 = parser.parseExpression(context, selectedValue);
        final Object value = (Object) expression2.execute(context);
        
        // コレクション、値が存在する場合のみ、以下の処理を実施
        if(collection != null && value != null){
            if (collection instanceof Collection) { 
            	// コレクションやリストから値に対応するラベルを検索する 
                Iterator iterator = ((Collection)collection).iterator(); 
                while (iterator.hasNext()) { 
                	LabelValue labelValue = (LabelValue)iterator.next(); 
                	if (labelValue.getValue().equals(value)) { 
                		label = labelValue.getLabel(); 
                		break; 
                	} 
                } 
            } 
        	else if (collection instanceof Map) {
              // マップから値に対応するラベルを検索する
              label = ((Map)collection).get(value);
            }
            else if (collection.getClass().isArray()) {
              // オブジェクトの配列から値に対応するラベルを検索する
              Object array[] = (Object[])collection;
              for (int i = 0; i < array.length; i++) {
                LabelValue labelValue = (LabelValue)array[i];
                if (labelValue.getValue().equals(value)) {
                  label = labelValue.getLabel();
                  break;
                }
              }
            }
        	
            if(label != null){
                structureHandler.setBody(label.toString(), false); 
            }
        }
   }
}
