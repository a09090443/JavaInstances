import com.tradevan.gateway.client.einv.util.InvoiceUtil;

public class TurnKeyApi {
	public static void main(String[] args) {
		InvoiceUtil invoice = new InvoiceUtil();
		System.out.println(invoice.getInvoiceEnvelopeFileName("v30", "C0401"));
		
	}
}
