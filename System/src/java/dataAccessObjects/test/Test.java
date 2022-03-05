package dataAccessObjects.test;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataAccessObjects.dao.*;
import dataAccessObjects.domain.Feature;
import java.util.List;
public class Test {
    
    public static void main(String[] args) {
        CompanyDao companyDao = new CompanyDao();
        LoginDao loginDao = new LoginDao();
        PositionDao positionDao = new PositionDao();
        FeatureDao featureDao = new FeatureDao();
        OffererDao offererDao = new OffererDao();
        PositionFeatureDao pfDao = new PositionFeatureDao();
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        
        List<Feature> features = featureDao.findFeaturesByParentId(null);
        String json = gson.toJson(features);
        
        System.out.println(json);
        
//        Feature feature = new Feature();
//        feature = featureDao.findById(4);
//        
//        System.out.println(feature.hasParent());
        
    }
    
}