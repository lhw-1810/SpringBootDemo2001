package com.hengxin.sbd.modules.test.common.vo;

public class Result<T> {
	private int status;
	private String message;
	private T object;

	public Result() {
		super();
	}

	public Result(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Result(int status, String message, T object) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", message=" + message + ", object=" + object + "]";
	}

	public enum ResultMenu {
		SUCCESS(200), FALSE(500);

		public int status;

		private ResultMenu(int status) {
			this.status = status;
		}

	}
}
