package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.ValueObject;
import eapli.framework.util.DateTime;
import eapli.framework.util.Strings;
import java.io.Serializable;
import java.util.regex.Pattern;
import javax.persistence.Embeddable;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Embeddable
public class MecanographicNumber implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Pattern constant with Employee's regex
     * Business Rule: Starts with a F and then has 6 digits
     */
    private static final Pattern VALID_EMPLOYEE_REGEX = Pattern.compile("^F[0-9]{6}$");
    
    /**
     * Pattern constant with Student's regex
     * Business Rule: The first two digits represents signing year and then has 5 more digits
     */
    private static Pattern VALID_STUDENT_REGEX;
    
    private String number;

    public MecanographicNumber(String mecanographicNumber) {
        VALID_STUDENT_REGEX = Pattern.compile(createStudentRegex());
        
        if (Strings.isNullOrEmpty(mecanographicNumber)) {
            throw new IllegalArgumentException("Mecanographic Number should neither be null nor empty");
        }
        
        if (!VALID_EMPLOYEE_REGEX.matcher(mecanographicNumber).find() && !VALID_STUDENT_REGEX.matcher(mecanographicNumber).find()) {
            throw new IllegalArgumentException("Invalid Mecanographic Number");
        }
        
        this.number = mecanographicNumber;
    }
    
    protected MecanographicNumber() {
        // for ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MecanographicNumber)) {
            return false;
        }

        final MecanographicNumber that = (MecanographicNumber) o;
        return this.number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return this.number.hashCode();
    }

    @Override
    public String toString() {
        return this.number;
    }
    
    public String number(){
        return this.number;
    }
    
    /**
     * Method that will create a new student's regex everytime that constructor is called
     * This will allow the application to have a limit of signing year (Present year)
     * 
     * @return Regex's string
     */
    private String createStudentRegex(){
        Integer thirdDigit = (DateTime.currentYear() % 100) / 10;
        String regex = "^(";
        
        for(int i = 0; i <= thirdDigit; i++){
            if(i < thirdDigit)
                regex += String.format("%s[0-9]|", i);
            else
                regex += String.format("%s[0-%s]", i, String.valueOf(DateTime.currentYear()).substring(3));
        }
        
        return regex + ")[0-9]{5}$";
    }
}
