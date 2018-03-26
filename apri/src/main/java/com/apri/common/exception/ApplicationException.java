package com.apri.common.exception;

import java.text.MessageFormat;

/**
 * アプリケーション例外クラス。
 */
public class ApplicationException extends RuntimeException {
    /** 例外原因 */
    protected Throwable cause;

    /**
     * コンストラクタ。
     * @param message この例外のメッセージです。
     */
    public ApplicationException(String message) {
        super(message);
        this.cause = null;
    }
    
    /**
     * 原因となった例外を指定してインスタンスを構築します。
     * @param cause この例外の原因となった例外です。
     */
    public ApplicationException(Throwable cause) {
      super(cause.getLocalizedMessage());
      this.cause = cause;
    }
    
    
    public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}
}
