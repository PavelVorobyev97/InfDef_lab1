package infodef_lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Infodef_lab1 {

    public static void main(String[] args) {

        Input input = new Input(); //создание алфавита и входнного слова
        StringBuilder output = new StringBuilder();
        StringBuilder output_freq = new StringBuilder();

        ArrayList<String> outputFreqArr = new ArrayList<>();

        //хэшмап для хранения символов и частоты их появления
        HashMap<Character, Double> freq = new HashMap<>();

        for (int i = 0; i < Input.lang.size(); i++) {
            freq.put(Input.lang.get(i), 0.0);
        }

        //считаем частоту символов 
        for (int i = 0; i < Input.input.length(); i++) {
            if (Character.UnicodeBlock.of(Input.input.charAt(i)) == Character.UnicodeBlock.CYRILLIC) {
                freq.put(Input.input.charAt(i), freq.get(Input.input.charAt(i)) + 1); //прибавление единицы к массиву значений 
            }
        }
        for (Map.Entry<Character, Double> entry : freq.entrySet()) {
            freq.put(entry.getKey(), entry.getValue() / Input.input.length());//делим на количество символов во входной строке
        }

        //шифруем сдвигом
        for (int i = 0; i < Input.input.length(); i++) { //цикл по входной строке
            if (Character.UnicodeBlock.of(Input.input.charAt(i)) == Character.UnicodeBlock.CYRILLIC) {
                for (int j = 0; j < Input.lang.size(); j++) { //цикл по алфавиту
                    if (Input.input.charAt(i) == Input.lang.get(j)) {
                        if (Input.offset + j >= Input.lang.size()) {
                            output.append(Input.lang.get(
                                    (j + Input.offset) - Input.lang.size()));
                            break;//???
                        } else {
                            output.append(Input.lang.get(j + Input.offset));
                            break;//???
                        }
                    }
                }
            } else {
                output.append(Input.input.charAt(i));
            }

        }

        //шифруем частотой
        for (int i = 0; i < Input.input.length(); i++) {
            if (Character.UnicodeBlock.of(Input.input.charAt(i)) == Character.UnicodeBlock.CYRILLIC) {
                output_freq.append(freq.get(Input.input.charAt(i)) + " ");

                outputFreqArr.add(freq.get(Input.input.charAt(i)) + " ");
            } else {
                output_freq.append(Input.input.charAt(i));

                outputFreqArr.add(Input.input.charAt(i) + "");
            }
        }

        //расшифровка частотного
        StringBuilder decodedFreq = new StringBuilder();

        for (String str : outputFreqArr) {
            for (Map.Entry<Character, Double> entry : freq.entrySet()) {

                if (str.length() > 1) {
                    if (Double.parseDouble(str) == entry.getValue()) {
                        decodedFreq.append(entry.getKey());
                        break;
                    }
                } else {
                    decodedFreq.append(str);
                    break;
                }

            }
        }

        /*
        //Биграммы
        HashMap<String, Double> freqBig = new HashMap<>();
        ArrayList<String> outputFreqBigArr = new ArrayList<>();
        StringBuilder outputFreqBigStr = new StringBuilder();
        
        //заполняем биграмами 
        for (int i = 0; i < Input.input.length() - 1; i++) {
            if (Character.UnicodeBlock.of(Input.input.charAt(i)) == Character.UnicodeBlock.CYRILLIC
                    && Character.UnicodeBlock.of(Input.input.charAt(i + 1)) == Character.UnicodeBlock.CYRILLIC) {
                freqBig.put(String.valueOf(Input.input.charAt(i)) + String.valueOf(Input.input.charAt(i + 1)), 0.0);
            }
        }

        //считаем количество биграмм 
        for (Map.Entry<String, Double> entry : freqBig.entrySet()) {
            for (int i = 0; i < Input.input.length() - 1; i++) {
                if ((String.valueOf(Input.input.charAt(i)) + String.valueOf(Input.input.charAt(i + 1))).equals(entry.getKey())) {
                    freqBig.put(entry.getKey(), entry.getValue() + 1);
                }
            }
        }
        for (Map.Entry<String, Double> entry : freqBig.entrySet()) { //делим на общее количество символов строки 
            freqBig.put(entry.getKey(), entry.getValue() / Input.input.length());
        }

        //шифруем биграмаами 
        for (int i = 0; i < Input.input.length() - 1; i ++ ) {
            if(Character.UnicodeBlock.of(Input.input.charAt(i)) == Character.UnicodeBlock.CYRILLIC
                    && Character.UnicodeBlock.of(Input.input.charAt(i + 1)) == Character.UnicodeBlock.CYRILLIC){
                
                String tmp = String.valueOf(Input.input.charAt(i)) + String.valueOf(Input.input.charAt(i + 1));
                
                outputFreqBigArr.add(freqBig.get(tmp).toString()); //в массив
                outputFreqBigStr.append(freqBig.get(tmp).toString()); //в строку
                
                i++;
                
            }
            if(Character.UnicodeBlock.of(Input.input.charAt(i + 1)) != Character.UnicodeBlock.CYRILLIC){
                outputFreqBigArr.add(String.valueOf(Input.input.charAt(i + 1)));
                outputFreqBigStr.append(String.valueOf(Input.input.charAt(i + 1)) + " ");
            }
                
        }
        
        //расшифровываем биграммы
        
        StringBuilder decodedBigFreq = new StringBuilder();
        for (String str : outputFreqBigArr) {
            for (Map.Entry<String, Double> entry : freqBig.entrySet()) {
                if (str.length() > 1) {
                    if (Double.parseDouble(str) == entry.getValue()) {
                        decodedBigFreq.append(entry.getKey());
                        break;
                    }
                } else {
                    decodedBigFreq.append(str);
                    break;
                }
            }
        }
        */
        
        /*
        System.out.println("========");
        System.out.println(freqBig);
        System.out.println(outputFreqBigArr);
        System.out.println(outputFreqBigStr+ " ");
        */
        

        //ДЛЯ ДЕБАГА
        //System.out.println(freq); //МАП с частотой 
        //System.out.println("Частота выход: " + output_freq);
        //System.out.println("Частота выход(лист): " + outputFreqArr);
        
        System.out.println("Вход: " + Input.input);
        System.out.println("Сдвиг выход: " + output);
        System.out.println("Частота выход: " + output_freq);
        System.out.println("Расшифровка частотного: " + decodedFreq);
        //System.out.println("Расшифровка частотного биграммы: " + decodedBigFreq);
        //System.out.println(freqBig);
        
        /*
        //расшифровываем сдвиг А ОНО НАДО ВООБЩЕ? ИБО НАФИГА?
        StringBuilder output2 = output;
        for(int i = 0; i <  output2.length(); i++){
            for (int j = 0; j < Input.lang.size(); j++) {
                if(output2.charAt(i) == Input.lang.get(j)){
                    output2.replace(j, j, Input.lang.get(j-2).toString());
                }
            }
        }
        System.out.println("Расшифровка: " + output2);
         */
    }

}
