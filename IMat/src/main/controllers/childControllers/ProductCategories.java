package main.controllers.childControllers;

import java.util.ArrayList;
import java.util.List;

import main.backend.CustomDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
/**
*
* @author Niclas
*/
public class ProductCategories {
	static Object[][] prodNameList = {
			{ProductCategory.BERRY,"Bär"},
			{ProductCategory.CABBAGE,"Kål"},
			{ProductCategory.CITRUS_FRUIT,"Citrus frukter"},
			{ProductCategory.EXOTIC_FRUIT,"Exotiska frukter"},
			{ProductCategory.FRUIT,"Frukt"},
			{ProductCategory.HERB,"Ärter"},
			{ProductCategory.MELONS,"Meloner"},
			{ProductCategory.ROOT_VEGETABLE,"Rotfrukter"},
			{ProductCategory.VEGETABLE_FRUIT,"Grönsaker"},
			{ProductCategory.FISH,"Fisk"},
			{ProductCategory.MEAT,"Kött"},
			{ProductCategory.COLD_DRINKS,"Kalla drycker"},
			{ProductCategory.NUTS_AND_SEEDS,"Nötter"},
			{ProductCategory.PASTA,"Pasta"},
			{ProductCategory.POD,"Baljväxter"},
			{ProductCategory.POTATO_RICE,"Potatis och Ris"},
			{ProductCategory.SWEET,"Godis"},
			{ProductCategory.HOT_DRINKS,"Varma drycker"},
			{ProductCategory.FLOUR_SUGAR_SALT,"Bakning"},
			{ProductCategory.BREAD,"Bröd"},
			{ProductCategory.DAIRIES,"Mejeri"}
			
	};
	static CustomDataHandler dataHandler = CustomDataHandler.getInstance();
	private static final ProductCategory[] VEG = {
			ProductCategory.BERRY,
			ProductCategory.CABBAGE,
			ProductCategory.CITRUS_FRUIT,
			ProductCategory.EXOTIC_FRUIT,
			ProductCategory.FRUIT,
			ProductCategory.HERB,
			ProductCategory.MELONS,
			ProductCategory.ROOT_VEGETABLE,
			ProductCategory.VEGETABLE_FRUIT};
	private static final ProductCategory[] PROTEIN = {
			ProductCategory.FISH,
			ProductCategory.MEAT
	};
	private static final ProductCategory[] KOLONIAL = {
			ProductCategory.COLD_DRINKS,
			ProductCategory.NUTS_AND_SEEDS,
			ProductCategory.PASTA,
			ProductCategory.POD,
			ProductCategory.POTATO_RICE,
			ProductCategory.SWEET,
			ProductCategory.HOT_DRINKS
	};
	private static final ProductCategory[] PANTRY = {
			ProductCategory.FLOUR_SUGAR_SALT
	};
	private static final ProductCategory[] BREAD = {
			ProductCategory.BREAD
	};
	private static final ProductCategory[] DAIRY = {
			ProductCategory.DAIRIES
			
	};
	
        public static ProductCategory[] getVeg(){
            return VEG;
        }
        public static ProductCategory[] getProt(){
            return PROTEIN;
        }
        public static ProductCategory[] getCol(){
            return KOLONIAL;
        }
        public static ProductCategory[] getPtry(){
            return PANTRY;
        }
        public static ProductCategory[] getBrd(){
            return BREAD;
        }
        public static ProductCategory[] getDry(){
            return DAIRY;
        }
	public static List<Product> getVegetables(){
		
		List<Product> result = new ArrayList<>();
		for(ProductCategory p: VEG){
			result.addAll(dataHandler.getProducts(p));
		}
		System.out.println(result.size());
		return result;
	}

	public static List<Product> getProtein() {
		List<Product> result = new ArrayList<>();
		for(ProductCategory p: PROTEIN){
			result.addAll(dataHandler.getProducts(p));
		}
		return result;
	}

	public static List<Product> getKolonial() {
		List<Product> result = new ArrayList<>();
		for(ProductCategory p: KOLONIAL){
			result.addAll(dataHandler.getProducts(p));
		}
		return result;
	}

	public static List<Product> getPantry() {
		List<Product> result = new ArrayList<>();
		for(ProductCategory p: PANTRY){
			result.addAll(dataHandler.getProducts(p));
		}
		return result;
	}

	public static List<Product> getBread() {
		List<Product> result = new ArrayList<>();
		for(ProductCategory p: BREAD){
			result.addAll(dataHandler.getProducts(p));
		}
		return result;
	}

	public static List<Product> getDairy() {
		List<Product> result = new ArrayList<>();
		for(ProductCategory p: DAIRY){
			result.addAll(dataHandler.getProducts(p));
		}
		return result;
	}

	public static String getProdCatName(ProductCategory prod){
		for(Object[] p : prodNameList){
			if(p[0].equals(prod)){
				return (String)p[1];
			}
		}
		
		return "";
		
	}
	
}
