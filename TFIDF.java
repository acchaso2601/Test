package tfidf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;

public class TFIDF {

	/**
	 * @param args
	 */
	static File data = new File("G:/eclipse/pleiades/workspace/Test/data/");
	static File ans;
	static HashMap<String,Integer> dfMap = new HashMap<String ,Integer>();
	static HashMap<String,Integer> wordCount ;
	static HashMap<Integer,HashMap<String,Integer>> tfMap = new HashMap<Integer,HashMap<String,Integer>>();
	
	public static void makeMap(File[] f) throws IOException{

		for(int i = 0;i<f.length;i++){
			wordCount= new HashMap<String ,Integer>();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f[i]),"MS932"));
			String line;
			HashSet<String> set = new HashSet<String>();
			//String fName = f[i].getName().replace(".txt", "");
			while((line=br.readLine())!=null){

				if(!set.contains(line)){
					set.add(line);
					if(!dfMap.containsKey(line)){
						dfMap.put(line, 1);
					}else{
						dfMap.put(line, dfMap.get(line)+1);
					}
				}
				if(!wordCount.containsKey(line)){
					wordCount.put(line, 1);
				}else{
					wordCount.put(line, wordCount.get(line)+1);
				}
			}
			tfMap.put(i, wordCount);
//			System.out.println(set.size()+"\t"+dfMap.size());

		}
//		System.out.println(dfMap);
	}
	public static void calTFIDF(File[] f) throws IOException{
		for(int i = 0;i<f.length;i++){
			//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f[i]),"MS932"));
			//String line;
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			File a = new File("G:/eclipse/pleiades/workspace/Test/answer/"+i+".txt");
			a.createNewFile();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(a),"MS932"));
			map = tfMap.get(i);
			for(String s :map.keySet()){
				bw.write(s+"\t"+map.get(s).doubleValue()*Math.log(f.length/dfMap.get(s).doubleValue())+"\n");

			}
			bw.close();
		}

	}
	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		long s = System.currentTimeMillis();
		ans = new File("G:/eclipse/pleiades/workspace/Test/answer/");
		ans.mkdirs();
		File[] f = data.listFiles();

			makeMap(f);
			calTFIDF(f);
			long g = System.currentTimeMillis();

			System.out.println("time:"+(g-s));
	}

}
