package ClassBean;

import covide19.HomePageController;
import hibernat.NewHibernateUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CovidDatas {
    
    //------------------------Attribute---------------------
    private int id;
    private String continent;
    private String country;
    private String dateA;
    private int total_Cases;
    private int new_Cases;
    private int total_Deaths;
    private int new_Deaths;
    private float reproduction_Rate;
    private int  new_Tests;
    private int  total_Tests;
    private int  total_Vaccinations;
    private int  people_Vaccinated;
    private int  people_Fully_Vaccinated;
    private int  new_Vaccinations;
    private float  stringency_Index;
    private float  population;
    private float  population_Density;
    private float  median_Age;
    private float  female_Smokers;
    private float  male_Smokers;
    
    //------------------------ getters & setters ---------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateA() {
        return dateA;
    }

    public void setDateA(String dateA) {
        this.dateA = dateA;
    }

    public int getTotal_Cases() {
        return total_Cases;
    }

    public void setTotal_Cases(int total_Cases) {
        this.total_Cases = total_Cases;
    }

    public int getNew_Cases() {
        return new_Cases;
    }

    public void setNew_Cases(int new_Cases) {
        this.new_Cases = new_Cases;
    }

    public int getTotal_Deaths() {
        return total_Deaths;
    }

    public void setTotal_Deaths(int total_Deaths) {
        this.total_Deaths = total_Deaths;
    }

    public int getNew_Deaths() {
        return new_Deaths;
    }

    public void setNew_Deaths(int new_Deaths) {
        this.new_Deaths = new_Deaths;
    }

    public float getReproduction_Rate() {
        return reproduction_Rate;
    }

    public void setReproduction_Rate(float reproduction_Rate) {
        this.reproduction_Rate = reproduction_Rate;
    }

    public int getNew_Tests() {
        return new_Tests;
    }

    public void setNew_Tests(int new_Tests) {
        this.new_Tests = new_Tests;
    }

    public int getTotal_Tests() {
        return total_Tests;
    }

    public void setTotal_Tests(int total_Tests) {
        this.total_Tests = total_Tests;
    }

    public int getTotal_Vaccinations() {
        return total_Vaccinations;
    }

    public void setTotal_Vaccinations(int total_Vaccinations) {
        this.total_Vaccinations = total_Vaccinations;
    }

    public int getPeople_Vaccinated() {
        return people_Vaccinated;
    }

    public void setPeople_Vaccinated(int people_Vaccinated) {
        this.people_Vaccinated = people_Vaccinated;
    }

    public int getPeople_Fully_Vaccinated() {
        return people_Fully_Vaccinated;
    }

    public void setPeople_Fully_Vaccinated(int people_Fully_Vaccinated) {
        this.people_Fully_Vaccinated = people_Fully_Vaccinated;
    }

    public int getNew_Vaccinations() {
        return new_Vaccinations;
    }

    public void setNew_Vaccinations(int new_Vaccinations) {
        this.new_Vaccinations = new_Vaccinations;
    }

    public float getStringency_Index() {
        return stringency_Index;
    }

    public void setStringency_Index(float stringency_Index) {
        this.stringency_Index = stringency_Index;
    }

    public float getPopulation() {
        return population;
    }

    public void setPopulation(float population) {
        this.population = population;
    }

    public float getPopulation_Density() {
        return population_Density;
    }

    public void setPopulation_Density(float population_Density) {
        this.population_Density = population_Density;
    }

    public float getMedian_Age() {
        return median_Age;
    }

    public void setMedian_Age(float median_Age) {
        this.median_Age = median_Age;
    }

    public float getFemale_Smokers() {
        return female_Smokers;
    }

    public void setFemale_Smokers(float female_Smokers) {
        this.female_Smokers = female_Smokers;
    }

    public float getMale_Smokers() {
        return male_Smokers;
    }

    public void setMale_Smokers(float male_Smokers) {
        this.male_Smokers = male_Smokers;
    }
    
    //------------------------ To string ---------------------
    @Override
    public String toString() {
        return "CovidDatas{" + "id=" + id + ", continent=" + continent + ", country=" + country + 
                ", dateA=" + dateA + ", total_Cases=" + total_Cases + ", new_Cases=" + new_Cases + 
                ", total_Deaths=" + total_Deaths + ", new_Deaths=" + new_Deaths + ", reproduction_Rate=" + 
                reproduction_Rate + ", new_Tests=" + new_Tests + ", total_Tests=" + total_Tests + ", total_Vaccinations=" + 
                total_Vaccinations + ", people_Vaccinated=" + people_Vaccinated + ", people_Fully_Vaccinated=" + people_Fully_Vaccinated + 
                ", new_Vaccinations=" + new_Vaccinations + ", stringency_Index=" + stringency_Index + ", population=" + population + 
                ", population_Density=" + population_Density + ", median_Age=" + median_Age + ", female_Smokers=" + female_Smokers + 
                ", male_Smokers=" + male_Smokers + '}';
    }

    //------------------------ afficher tout les informations ---------------------
    public static List getAllData(){
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession(); 
        session.beginTransaction();
        
        List listPr = session.createQuery("from CovidDatas where id between 0 and 20 order by id").list();
        //session.close();
        return listPr;
    }
    
    //------------ get total case & new case & total décès & nouveau décè 
    //------------------------ & total test & new test ---------------------
    public static Number getdataLineCharts(String date, String columN, String Country, String continent){
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession(); 
        session.beginTransaction();
        
        List<Number> listPr = session.createQuery("select sum("+columN+")from CovidDatas where dateA like"+
                date+" and country = '"+Country+"' and continent = '"+continent+"'").list();
        Number res = listPr.get(0);
        session.close();
        
        if(res == null){
            return 0;
        }
        return res;
    }
    
    //------------------------ get total_Vaccination & People_Vaccinated & People_fully_Vaccinated ---------------------
    public static Number getdataPieChart(String date, String columN, String Country, String continent){
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession(); 
        session.beginTransaction();
        
        List<Number> listPr = session.createQuery("select Max("+columN+")from CovidDatas where dateA like"+
                date+" and country = '"+Country+"' and continent = '"+continent+"'").list();
        Number res = listPr.get(0);
        session.close();
        System.out.println("MAX => "+res);
        if(res == null){
            return 0;
        }
 
        return res;
    }
    
    //------------------------ get new case & nouveau décès & new test ---------------------
    //------------------------ female_Smokers & male_Smokers & median_Age & population_Density---------------------
    public static Number getdataBarCharts(String date, String columN, String Country, String continent){
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession(); 
        session.beginTransaction();
        
        List<Number> listPr = session.createQuery("select "+columN+" from CovidDatas where dateA like '"+
                date+"' and country = '"+Country+"' and continent = '"+continent+"'").list();
        if(listPr.isEmpty()){
            session.close();
            return null;    
        }
        Number res = listPr.get(0);
        
        if(res == null){
            return 0;
        }
        session.close();
        return res;
    }
    
    //------------------------ get total case ou total deaths ou total test  ---------------------
    public static Number getStaticNumber(String columN){
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession(); 
        session.beginTransaction();
        
        List listPr = session.createQuery("select sum("+columN+")from CovidDatas").list();
        
        Number res = (Long)listPr.get(0);
        session.close();
        return res;
    }
    
    //------------------------ get all continent ---------------------
    public static List getAllContinent(){
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession(); 
        session.beginTransaction();
        
        List listPr = session.createQuery("select distinct continent from CovidDatas order by continent").list();
        
        session.close();
        return listPr;
    }
    
    //------------------------ get all contry by continent ---------------------
    public static List getAllCountryByContinent(String continent){
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession(); 
        session.beginTransaction();
        
        List listPr = session.createQuery("select distinct country from CovidDatas where continent like '"+
                continent+"' order by country").list();
        
        session.close();
        return listPr;
    }
  
}
