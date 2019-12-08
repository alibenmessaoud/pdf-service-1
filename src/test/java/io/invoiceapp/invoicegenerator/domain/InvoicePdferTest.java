package io.invoiceapp.invoicegenerator.domain;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InvoicePdferTest {

  private InvoicePdfer pdfer;

  @Test
  void complex() throws IOException, DocumentException {

    Invoice invoice = new Invoice();

    Map<String, Object> data = new HashMap<>();
    data.put("companyLogo", "");
    data.put("company_name", "Awar");
    data.put("company_address", "227 Cobblestone Road");
    data.put("company_city_zip_state", "30000 Bedrock, Cobblestone County");
    data.put("company_phone_fax", "Phone: +1 780 546 2222 - Fax: +1 780 546 2424");
    data.put("company_email_web", "www.dinostore.bed - hello@dinostore.bed");
    data.put("payment_info_label", "Payments");
    data.put("payment_info", List.of("Account Number - 123006705", "IBAN - US100000060345"));
    data.put("invoice_title", "Fatoura");
    data.put("invoice_number", "2000");
    data.put("issue_date_label", "Issue on");
    data.put("issue_date", "01/12/2019");
    data.put("due_date_label", "Due on");
    data.put("due_date", "01/01/2020");
    data.put("currency_label", "* All prices are in ");
    data.put("currency", "USD");
    data.put("invoice_reference_label", "Reference");
    data.put("invoice_reference", "Order #1234");
    data.put("bill_to_label", "Client");
    data.put("client_name", "Slate Rock");
    data.put("client_address", "222 Rocky Way");
    data.put("client_city_zip_state", "30000 Bedrock, Cobblestone County");
    data.put("client_phone_fax", "+1 77 22 555 666");
    data.put("client_email", "fred@slaterockgravel.bed");
    data.put("client_other", List.of("Attn: Fred Flintstone"));
    data.put("item_row_number_label", "");
    data.put("item_description_label", "Item");
    data.put("item_quantity_label", "Qty");
    data.put("item_price_label", "Unit price");
    data.put("item_discount_label", "Discount");
    data.put("item_tax_label", "Tax");
    data.put("item_line_total_label", "Linetotal");
    data.put("amount_subtotal_label", "Subtotal");
    data.put("amount_subtotal", "1000");
    data.put("tax_name", "Tax");
    data.put("tax_value", "100");
    data.put("amount_total_label", "");
    data.put("amount_total", "1100");
    data.put("amount_paid_label", "Paid");
    data.put("amount_paid", "0");
    data.put("amount_due_label", "Due sum");
    data.put("amount_due", "1100");
    data.put("terms_label", "Terms");
    data.put(
        "terms",
        "Fred, thank you very much. We really appreciate your business. Please send payments before the due date.");

    Map<String, Object> item1 = new HashMap<>();
    item1.put("itemRowNumber", "1");
    item1.put("itemDescription", "Product 1");
    item1.put("itemQuantity", "1");
    item1.put("itemPrice", "1000");
    item1.put("itemDiscount", "0");
    item1.put("itemTax", "0");
    item1.put("itemLineTotal", "1000");

    data.put("items", List.of(item1));
    invoice.setData(data);
    pdfer = new InvoicePdfer();
    pdfer.prdo(invoice, "m1/complex");
  }

  @Test
  void complex_json() throws IOException, DocumentException {
    Gson gson = new Gson();
    Invoice invoice =
        gson.fromJson(
            "{\"data\":{\"amount_paid\":\"0\",\"item_row_number_label\":\"\",\"invoice_title\":\"Fatoura\",\"client_city_zip_state\":\"30000 Bedrock, Cobblestone County\",\"amount_total\":\"1100\",\"client_other\":[\"Attn: Fred Flintstone\"],\"company_address\":\"227 Cobblestone Road\",\"company_email_web\":\"www.dinostore.bed - hello@dinostore.bed\",\"bill_to_label\":\"Client\",\"client_phone_fax\":\"+1 77 22 555 666\",\"item_description_label\":\"Item\",\"issue_date\":\"01/12/2019\",\"item_line_total_label\":\"Linetotal\",\"tax_value\":\"100\",\"terms\":\"Fred, thank you very much. We really appreciate your business. Please send payments before the due date.\",\"invoice_reference\":\"Order #1234\",\"currency\":\"USD\",\"amount_due\":\"1100\",\"payment_info\":[\"Account Number - 123006705\",\"IBAN - US100000060345\"],\"currency_label\":\"* All prices are in \",\"client_address\":\"222 Rocky Way\",\"amount_subtotal_label\":\"Subtotal\",\"invoice_number\":\"2000\",\"issue_date_label\":\"Issue on\",\"client_name\":\"Slate Rock\",\"terms_label\":\"Terms\",\"company_phone_fax\":\"Phone: +1 780 546 2222 - Fax: +1 780 546 2424\",\"invoice_reference_label\":\"Reference\",\"item_price_label\":\"Unit price\",\"companyLogo\":\"\",\"tax_name\":\"Tax\",\"due_date\":\"01/01/2020\",\"client_email\":\"fred@slaterockgravel.bed\",\"amount_due_label\":\"Due sum\",\"amount_paid_label\":\"Paid\",\"amount_total_label\":\"\",\"company_city_zip_state\":\"30000 Bedrock, Cobblestone County\",\"item_discount_label\":\"Discount\",\"company_name\":\"Awar\",\"item_quantity_label\":\"Qty\",\"payment_info_label\":\"Payments\",\"item_tax_label\":\"Tax\",\"items\":[{\"itemQuantity\":\"1\",\"itemDiscount\":\"0\",\"itemPrice\":\"1000\",\"itemTax\":\"0\",\"itemDescription\":\"Product 1\",\"itemRowNumber\":\"1\",\"itemLineTotal\":\"1000\"}],\"amount_subtotal\":\"1000\",\"due_date_label\":\"Due on\"}}",
            Invoice.class);
    pdfer = new InvoicePdfer();
    pdfer.prdo(invoice, "m1/complex");
  }

  @Test
  void sample() throws IOException, DocumentException {
    pdfer = new InvoicePdfer();
    pdfer.prdo(null, "m2/sample");
  }
}
