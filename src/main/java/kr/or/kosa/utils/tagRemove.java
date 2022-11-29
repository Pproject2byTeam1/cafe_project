package kr.or.kosa.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tagRemove {

	public String htmlTagRemoveString(String data) {
		
        // [리턴 데이터 변수 선언 실시]
        String result = "";

        // [문자열 데이터 널 판단 수행 실시]
        if (data != null
                && data.length()>0
                && data.trim().equals("") == false
                && data.trim().equals("null") == false
                && data.trim().equals("undefined") == false){ // [널이 아닌 경우]

            try {
            	// [html 태그를 제거 하기 위한 패턴 정의 실시]
            	String tag_pattern = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>"; // [<p> 등 태그 제거]
            	Pattern num_reg_entity_pattern = Pattern.compile("&#[0-9]+;"); // [&#09; 형태 제거]
            	Pattern char_reg_entity_pattern = Pattern.compile("&[a-zA-Z]+;"); // [&amp; 형태 제거]
            	Pattern char_normal_entity_pattern = Pattern.compile(" [a-zA-Z]+;"); // [amp; 형태 제거]
            	
            	
                // [html 태그 1차 제거 실시]
                result = data.replaceAll(tag_pattern, " ");

                
                // [html 태그 2차 제거 실시]
                Matcher num_reg_mat = num_reg_entity_pattern.matcher(result);
                result = num_reg_mat.replaceAll("");
                
                Matcher char_reg_mat = char_reg_entity_pattern.matcher(result);
                result = char_reg_mat.replaceAll("");
                
                Matcher char_normal_mat = char_normal_entity_pattern.matcher(result);
                result = char_normal_mat.replaceAll("");
                
                
                // [html 태그 연속 공백 제거 실시]
                result = result.replaceAll("\\s+", " ");

                // [문자열 양쪽 끝 공백 제거 실시]
                result = result.trim();
                
                // [문자열 글자수 자르기 실시]
                //result = result.substring(0,30); 
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


        // [리턴 데이터 반환 실시]
        return result;
	}

} // [클래스 종료]