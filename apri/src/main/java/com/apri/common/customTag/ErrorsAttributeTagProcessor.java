package com.apri.common.customTag;

import java.util.List;

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

public class ErrorsAttributeTagProcessor extends AbstractAttributeTagProcessor {

    private static final String ATTR_NAME = "errors";
    private static final int PRECEDENCE = 10000;

    public ErrorsAttributeTagProcessor(final String dialectPrefix) {
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

    	// メッセージの抽出
        final IEngineConfiguration configuration = context.getConfiguration();
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(context, attributeValue);
        final List error_list = (List) expression.execute(context);
        
        // タグの抽出
        String outerTag = tag.getAttributeValue("outerTag") == null ? "ul" : tag.getAttributeValue("outerTag");
        String innerTag = tag.getAttributeValue("innerTag") == null ? "li" : tag.getAttributeValue("innerTag");

        // メッセージが存在する場合のみ、以下の処理を実施
        if(error_list != null && error_list.size() != 0){
            // モデルの作成
        	IModelFactory modelFactory = context.getModelFactory(); 
            IModel model = modelFactory.createModel();

            // <ul>タグの設定
            model.add(modelFactory.createOpenElementTag(outerTag));
            
            // メッセージ数分処理を実施
            for(int i=0;i<error_list.size();i++){
            	String value = (String)error_list.get(i);
                model.add(modelFactory.createOpenElementTag(innerTag,"style","color: red"));
                model.add(modelFactory.createText(value));
                model.add(modelFactory.createCloseElementTag(innerTag));
            }
            
            // </ul>タグの設定
            model.add(modelFactory.createCloseElementTag(outerTag));
            
//            structureHandler.setBody(model, false); 
            structureHandler.replaceWith(model, false);
        }
   }
}
