package com.apri;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

// プロジェクトを他のプロジェクトで使用したとしても、このファイルは必ず残す。
// フォルダが分かれていてもファイル名が同じだとエラーが発生する。
// このプログラムでは、パス付きファイル名を作成してくれるので、上記エラーの発生を防ぐ
public class FQCNBeanNameGenerator extends AnnotationBeanNameGenerator {
	
    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        return definition.getBeanClassName();
    }
}
