package net.zkbc.p2p.fep.message.protocol;

/**
 * 获取投资信息.客户端请求
 * 
 * @author 代码生成器v1.0
 * 
 */
public class GetPrjCustBuyByPrjIdRequest extends RequestSupport {

	private String prjId;

	public GetPrjCustBuyByPrjIdRequest() {
		super();
		setMessageId("getPrjCustBuyByPrjId");
	}	

	/**
	 * @return 项目id
	 */
	public String getPrjId() {
		return prjId;
	}

	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
}