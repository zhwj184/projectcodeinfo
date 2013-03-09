/**
 * Project: projectInfo
 * 
 * File Created at 2010-6-7
 * $Id$
 * 
 * Copyright 2008 Alibaba.com Croporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package projectinfo.popup.actions;

/**
 * TODO Comment of CountCodeLines
 * @author weijian.zhongwj
 *
 */
import java.io.File;    
import java.io.FileNotFoundException;    
import java.io.BufferedReader;    
import java.io.FileReader;    
import java.io.IOException;    
   
   
public class CountCodeLines    
{    
    static int codeLines=0;    
    static int whiteLines=0;    
    static int commentLines=0;    
    static int tatolLines=0;   
    static int classNumber = 0;
    static boolean bComment=false;    
    
    
    public static void getCodeLines(String path)     
    {   
    	codeLines = 0;
    	whiteLines = 0;
    	commentLines = 0;
    	tatolLines = 0;
    	classNumber = 0;
        StringBuffer pathName=new StringBuffer(path);    
        ComputeDirectoryAndFiles(pathName,0);    
        System.out.println("Code Lines : "+(codeLines=tatolLines-commentLines-whiteLines));    
        System.out.println("White Lines : "+whiteLines);    
        System.out.println("Comment Lines : "+commentLines);    
   
    }    
   
    public static void ComputeDirectoryAndFiles(StringBuffer pathName,int level){    
        File directory=new File(pathName.toString());    
        File[] files=directory.listFiles();    
        String prefix="";    
        for(int i=0;i<level;i++)    
        {    
            prefix+="** ";    
        }    
        if(directory.isDirectory()){    
            for(int i=0;i<files.length;i++)    
            {       
                if(files[i].isFile()&& files[i].getName().matches("^[a-zA-Z[^0-9]]\\w*.java$"))    
                {    
                    classNumber++;    
                    computeLines(files[i]);    
                }    
                if(files[i].isDirectory())    
                {                   
                        
                    pathName.append("/"+files[i].getName());    
                    level++;    
                    ComputeDirectoryAndFiles(pathName,level);    
                    int start=pathName.toString().length()-files[i].getName().length()-1;    
                    int end=pathName.toString().length();    
                    pathName.delete(start,end);                     
                        
                    level--;    
                }    
            }    
        }    
    }    
    public static void computeLines(File file)    
    {       
        BufferedReader bf=null;    
            
        try   
        {    
            bf=new BufferedReader(new FileReader(file));    
            String lineStr="";    
            while((lineStr=bf.readLine())!=null)    
            {    
                //总行数    
                tatolLines++;    
                //计算空行    
                whiteLines(lineStr);    
                //统计代码行数    
                commendLines(lineStr);    
                //计算代码的行数    
                //codeLines(lineStr);    
            }    
        }    
        catch (FileNotFoundException e)    
        {    
            System.out.println("文件没有找到");    
        }catch(IOException ee)    
        {    
            System.out.println("输入输出异常　");    
        }finally   
        {    
            if(bf!=null)    
            {    
                try{    
                bf.close();    
                bf=null;    
                }catch(Exception e)    
                {    
                    System.out.println("关闭BufferReader时出错");    
                }    
            }    
        }    
    }    
    public static void whiteLines(String lineStr)    
    {    
        if(lineStr.matches("^[\\s&&[^\\n]]*$"))    
        {    
                    whiteLines++;    
        }    
   
    }    
    public static void commendLines(String lineStr)    
    {    
            
                //判断是否是一个注释行    
                //这里是单行注释的如 /*..... */或/**.... */    
                if(lineStr.matches("\\s*/\\*{1,}.*(\\*/).*"))    
                {    
        
                    commentLines++;    
                }    
                /**   
                    这里是多行注释的   
                */   
                //这里的是当开始为/**或/*但是没有 */ 关闭时     
                else if(lineStr.matches("\\s*/\\*{1,}.*[^\\*/].*"))    
                {    
                    
                    commentLines++;    
                    bComment=true;    
                }    
                else if(true==bComment)    
                {    
                        
                    commentLines++;    
                    if(lineStr.matches("\\s*[\\*/]+\\s*"))    
                    {    
                        bComment=false;    
                    }    
                }    
                else if(lineStr.matches("^[\\s]*//.*"))    
                {    
            
                    commentLines++;    
                }    
   
    }    
    //public static void codeLines(String lineStr)    
    //{    
    //  if(lineStr.matches("\\s*[[^/\\*]|[//]]+.*"))    
    //  codeLines++;    
    //}    
}   

