package com.leaf.cloud.config.auth.base;

public abstract class ResultLeaf extends Leaf{

    public static Leaf success(Object data){
       return result(true,data,null,null);
    }
    public static Leaf success(Object data,String message){
        return result(true,data,null,message);
    }

    public static Leaf fail(Object data){
        return result(false,data,null,null);
    }
    public static Leaf fail(Object data,String message){
        return result(false,data,null,message);
    }
    public static Leaf fail(Object data,Exception e,String message){
        return result(false,data,e,message);
    }

    public static Leaf fail(Exception e){
        return result(false,null,e,e.getMessage());
    }

    public static Leaf result(boolean isSuccess,Object data,Exception e,String message){
        Leaf leaf = new Leaf();
        leaf.setSuccess(isSuccess);
        leaf.setData(data);
        leaf.setException(e);
        leaf.setMessage(message);

        return leaf;
    }

}
