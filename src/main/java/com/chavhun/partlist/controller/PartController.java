package com.chavhun.partlist.controller;

import com.chavhun.partlist.model.Part;
import com.chavhun.partlist.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@Controller
public class PartController {
    private int numPage;
    private List<Part> listPartOnPage;
    private int numSorted;//0-Стандартная сортировка 1 - Необходимые детали 2 - опциональные детали

    //--------------------------------------------------------------------------------------
    private PartService partService;
    @Autowired(required = true) //Автоматическое связывание
    @Qualifier(value = "partService") //Создаёт обьект partService для управления базой данных
    public void setPartService(PartService partService) {
        this.partService = partService;
        numPage = 0;
        numSorted = 0;
        getStartPaging();
    }
    //--------------------------------------------------------------------------------------


     @RequestMapping(method = RequestMethod.GET) //Домашняя
     public String homePage(Model model){
         /*model.addAttribute("part", new Part());
         model.addAttribute("listParts", partService.listParts());*/
         //model.addAttribute("objectNameForm", new NameForm());
         //getStartPaging();
         return "redirect:/parts";
     }
    @RequestMapping(value = "parts", method = RequestMethod.GET) //Анотация определяет адресс по которому метод будет доступен в клиенте(Адрессной строке)
    public String listParts(Model model){
        model.addAttribute("part", new Part());
        model.addAttribute("listParts", listPartOnPage);
        model.addAttribute("countComputers", countComputers());
        return "parts";
    }

    @RequestMapping(value = "/parts/add", method = RequestMethod.POST)
    public String addPart(@ModelAttribute("part") Part part){
        Part sameNamePart = sameName(part.getName());
        if(sameNamePart!=null && sameNamePart.getId()!=part.getId()){
            System.out.println("Одинаковые имена");
            part.setCount(sameNamePart.getCount()+part.getCount());
            part.setId(sameNamePart.getId());
            partService.updatePart(part);
        }else {
            if (part.getId() == 0) {
                partService.addPart(part);
            } else {
                partService.updatePart(part);
            }
        }
        getStartPaging();
        return "redirect: /parts";
    }

    @RequestMapping("/remove/{id}")
    public String removePart(@PathVariable("id") int id){
        partService.removePart(id);
        getStartPaging();
        return "redirect:/parts";
    }

    @RequestMapping("edit/{id}")
    public String editPart(@PathVariable("id") int id, Model model){
        model.addAttribute("part", this.partService.getPartById(id));
        model.addAttribute("listParts", listPartOnPage);

        return "parts";
    }



    @RequestMapping("partdata/{name}")
    public String partData(@PathVariable("name") String name, Model model){
        model.addAttribute("part", sameName(name));
        return "partdata";
    }

    @RequestMapping("/next")
    public String nextPage(Model model){
        nextOrPrevious("next");
        model.addAttribute("listParts", listPartOnPage);
        return "redirect:parts";
    }

    @RequestMapping("/previous")
    public String previousPage(Model model){
        nextOrPrevious("previous");
        model.addAttribute("listParts", listPartOnPage);
        return "redirect:parts";
    }
    @RequestMapping("/standardSort")
    public String standardSort(){
         numSorted = 0;
         getStartPaging();
         return "redirect:/parts";
    }
    @RequestMapping("/onlyNecessSort")
    public String onlyNecessSort(){
        numSorted = 1;
        getStartPaging();
        return "redirect:/parts";
    }
    @RequestMapping("/NotNecessSort")
    public String NotNecessSort(){
        numSorted = 2;
        getStartPaging();
        return "redirect:/parts";
    }
    @RequestMapping(value = "parts/search")
    public String search(Model model, @ModelAttribute("partName") Part part){
        System.out.println("------" + part.getName());
         Part partSearch = sameName(part.getName());
         List<Part> serchList = new ArrayList<Part>();
         serchList.add(partSearch);
         if(partSearch!=null)
         model.addAttribute("listParts", serchList);
         else {
             model.addAttribute("listParts", listPartOnPage);
             model.addAttribute("msg", "Совпадений не найдено.");
         }
         model.addAttribute("part", new Part());

         return "parts";
    }




    private int countComputers(){
        List<Part> parts = partService.listParts();
        int minCount = Integer.MAX_VALUE;
        for(Part part:parts){
            if(part.getNecessarily().equalsIgnoreCase("Да") && part.getCount()<minCount)
                minCount = part.getCount();
        }
        return minCount == Integer.MAX_VALUE? 0: minCount;
    }

    private Part  sameName(String name){
        for(Part part1: partService.listParts()){
            if(part1.getName().equalsIgnoreCase(name)){
                return part1;
            }
        }
        return null;
    }
    private void nextOrPrevious(String msg){
        List<Part> parts = getSortedList();
        List<Part> partsTen = new ArrayList<Part>();
        if(msg.equalsIgnoreCase("next")){
            if(numPage+10<parts.size())
            numPage=numPage+10;

            if(parts.size()<numPage+10){
                for (int i = numPage; i < parts.size(); i++) {
                    partsTen.add(parts.get(i));
                }
            }else{
                for (int i = numPage; i < numPage+10; i++) {
                    partsTen.add(parts.get(i));
                }
            }
            listPartOnPage = partsTen;
        }else if(msg.equalsIgnoreCase("previous")){
            if(numPage-10>=0) {
                numPage -= 10;
            }
            if(parts.size()>10) {
                for (int i = numPage; i < numPage + 10; i++) {
                    partsTen.add(parts.get(i));
                }
            }else{
                for (int i = numPage; i < parts.size(); i++) {
                    partsTen.add(parts.get(i));
                }
            }

            listPartOnPage = partsTen;
        }
    }
    private void getStartPaging(){
        List<Part> parts = getSortedList();
         List<Part> listPart = new ArrayList<Part>();
         if(parts.size()>10){
        for (int i = 0; i < 10; i++) {
            listPart.add(parts.get(i));
        }
         }else{
             for (int i = 0; i < parts.size(); i++) {
                 listPart.add(parts.get(i));
             }
        }
        numPage = 0;
      listPartOnPage = listPart;

    }

    private List<Part> getSortedList(){
         ArrayList<Part> listSorted = new ArrayList<Part>();
        if(numSorted ==0){
            return partService.listParts();
        }else if(numSorted == 1){
            for(Part part: partService.listParts()) {
                if (part.getNecessarily().equalsIgnoreCase("Да")) {
                    listSorted.add(part);
                }
            }
            return listSorted;
        }else if(numSorted == 2){
            for(Part part: partService.listParts()) {
                if (!part.getNecessarily().equalsIgnoreCase("Да")) {
                    listSorted.add(part);
                }
            }
            return listSorted;
        }
        return null;
    }

}
