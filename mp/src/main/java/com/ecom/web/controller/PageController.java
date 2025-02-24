package com.ecom.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.web.factory.GuitarFactory;
import com.ecom.web.factory.PedalFactory;
import com.ecom.web.model.Product;

@Controller
public class PageController implements ErrorController {

  @Autowired
  private GuitarFactory guitarFactory;

  @Autowired
  private PedalFactory pedalFactory;

  @GetMapping("/guitar/{name}")
  public String getGuitar(@PathVariable String name, Model model) {
    Product product = guitarFactory.createProduct(name);
    model.addAttribute("product", product);
    return "product";
  }

  @GetMapping("/pedal/{name}")
  public String getPedal(@PathVariable String name, Model model) {
    Product product = pedalFactory.createProduct(name);
    model.addAttribute("product", product);
    return "product";
  }

  @GetMapping("/index")
  public String indexPage() {
    return "index";
  }

  @GetMapping("/thbb10")
  public String thbb10Page() {
    return "thbb10";
  }

  @GetMapping("/tod10")
  public String tod10Page() {
    return "tod10";
  }

  @GetMapping("/tod10n")
  public String tod10nPage() {
    return "tod10n";
  }

  @GetMapping("/guitars")
  public String guitarsPage() {
    return "guitars";
  }

  @GetMapping("/drumkits")
  public String drumkitsPage() {
    return "drumkits";
  }

  @GetMapping("/pedals")
  public String pedalsPage() {
    return "pedals";
  }

  @GetMapping("/ts9")
  public String ts9Page() {
    return "ts9";
  }

  @GetMapping("/dc2")
  public String dc2Page() {
    return "dc2";
  }

  @GetMapping("/looper")
  public String looperPage() {
    return "looper";
  }

  @GetMapping("/amplifiers")
  public String amplifiersPage() {
    return "amplifiers";
  }

  @GetMapping("/accessories")
  public String accessoriesPage() {
    return "accessories";
  }

  @GetMapping("/shipping")
  public String orderPage() {
    return "finalorder";
  }

  @GetMapping("/error")
  public String handleError() {
    return "error";
  }
}