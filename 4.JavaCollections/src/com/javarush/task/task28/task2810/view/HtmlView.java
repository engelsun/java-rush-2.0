package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

/**
 * Created by engelsun on 6/15/2017.
 */
public class HtmlView implements View {
    private Controller controller;

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document doc = null;
        try {
            doc = getDocument();
            Element templateOriginal = doc.getElementsByClass("template").first();
            Element copyTemplate = templateOriginal.clone();
            copyTemplate.removeAttr("style");
            copyTemplate.removeClass("template");
            doc.select("tr[class=vacancy]").remove().not("tr[class=vacancy template");

            for (Vacancy vacancy : vacancies) {

                Element localClone = copyTemplate.clone();

                localClone.getElementsByClass("city").first().text(vacancy.getCity());
                localClone.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                localClone.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element link = localClone.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateOriginal.before(localClone.outerHtml());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return doc.html();
    }

    private void updateFile(String s) {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(s.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }
}
