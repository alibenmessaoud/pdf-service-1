package io.invoiceapp.invoicegenerator.domain;

import com.itextpdf.text.DocumentException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class InvoicePdfer {

  void prdo(Invoice invoice, String template) throws IOException, DocumentException {

    Context contextModel = new Context();
    if (invoice != null) {
      Map<String, Object> data = invoice.getData();
      Set<String> keys = data.keySet();
      keys.forEach(key -> contextModel.setVariable(key, invoice.getData().get(key)));
    }

    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("XHTML");
    templateResolver.setCharacterEncoding("UTF-8");

    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);

    String htmlContent = templateEngine.process(template, contextModel);

    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(htmlContent);
    renderer.layout();

    OutputStream outputStream =
        new FileOutputStream("pdfs/" + UUID.randomUUID().toString() + ".pdf");
    renderer.createPDF(outputStream);
    outputStream.close();
  }
}
