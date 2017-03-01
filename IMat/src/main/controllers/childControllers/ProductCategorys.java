package main.controllers.childControllers;

import java.util.ArrayList;
import java.util.List;

import main.backend.CustomDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

public class ProductCategorys {
	static CustomDataHandler dataHandler = CustomDataHandler.getInstance();
	public static final ProductCategory[] VEG = {
			ProductCategory.BERRY,
			ProductCategory.CABBAGE,
			ProductCategory.CITRUS_FRUIT,
			ProductCategory.EXOTIC_FRUIT,
			ProductCategory.FRUIT,
			ProductCategory.HERB,
			ProductCategory.MELONS,
			ProductCategory.ROOT_VEGETABLE,
			ProductCategory.VEGETABLE_FRUIT};
	public static final ProductCategory[] PROTIN = {
			ProductCategory.FISH,
			ProductCategory.MEAT
	};
	public static final ProductCategory[] KOLONIAL = {
			ProductCategory.COLD_DRINKS,
			ProductCategory.NUTS_AND_SEEDS,
			ProductCategory.PASTA,
			ProductCategory.POD,
			ProductCategory.POTATO_RICE,
			ProductCategory.SWEET,
			ProductCategory.HOT_DRINKS
	};
	public static final ProductCategory[] PANTRY = {
			ProductCategory.FLOUR_SUGAR_SALT
	};
	public static final ProductCategory[] BREAD = {
			ProductCategory.BREAD
	};
	public static final ProductCategory[] DAIRY = {
			ProductCategory.DAIRIES
			
	};
	
	public static List<Product> getVegetables(){
		
		List<Product> result = new ArrayList<>();
		for(ProductCategory p: VEG){
			result.addAll(dataHandler.getProducts(p));
		}
		return result;
	}
	
}
