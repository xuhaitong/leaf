/*     */ package net.siufung.boot.mybatis.support.plugins;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.ibatis.mapping.MappedStatement;
/*     */ import org.apache.ibatis.reflection.MetaObject;
/*     */ import org.apache.ibatis.reflection.SystemMetaObject;
/*     */ 
/*     */ public final class PluginUtils
/*     */ {
/*     */   public static final String DELEGATE_MAPPEDSTATEMENT = "delegate.mappedStatement";
/*     */ 
/*     */   public static MappedStatement getMappedStatement(MetaObject metaObject)
/*     */   {
/*  38 */     return (MappedStatement)metaObject.getValue("delegate.mappedStatement");
/*     */   }
/*     */ 
/*     */   public static Object realTarget(Object target)
/*     */   {
/*  47 */     if (Proxy.isProxyClass(target.getClass())) {
/*  48 */       MetaObject metaObject = SystemMetaObject.forObject(target);
/*  49 */       return realTarget(metaObject.getValue("h.target"));
/*     */     }
/*  51 */     return target;
/*     */   }
/*     */ 
/*     */   public static String getProperty(Properties properties, String key)
/*     */   {
/*  60 */     String value = properties.getProperty(key);
/*  61 */     return StringUtils.isEmpty(value) ? null : value;
/*     */   }
/*     */ 
/*     */   public static <T> T getValueByFieldType(Object obj, Class<T> fieldType)
/*     */   {
/*  66 */     Object value = null;
/*  67 */     for (Class superClass = obj.getClass(); superClass != Object.class; )
/*     */     {
/*     */       try {
/*  70 */         Field[] fields = superClass.getDeclaredFields();
/*  71 */         for (Field f : fields) {
/*  72 */           if (f.getType() == fieldType) {
/*  73 */             if (f.isAccessible()) {
/*  74 */               value = f.get(obj);
/*  75 */               break;
/*     */             }
/*  77 */             f.setAccessible(true);
/*  78 */             value = f.get(obj);
/*  79 */             f.setAccessible(false);
/*  80 */             break;
/*     */           }
/*     */         }
/*     */ 
/*  84 */         if (value != null)
/*  85 */           break;
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*  68 */       superClass = superClass
/*  68 */         .getSuperclass();
/*     */     }
/*     */ 
/*  90 */     return value;
/*     */   }
/*     */ 
/*     */   public static Object getValueByFieldName(Object obj, String fieldName)
/*     */   {
/* 100 */     Object value = null;
/*     */     try {
/* 102 */       Field field = getFieldByFieldName(obj, fieldName);
/* 103 */       if (field != null)
/* 104 */         if (field.isAccessible()) {
/* 105 */           value = field.get(obj);
/*     */         } else {
/* 107 */           field.setAccessible(true);
/* 108 */           value = field.get(obj);
/* 109 */           field.setAccessible(false);
/*     */         }
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/* 114 */     return value;
/*     */   }
/*     */ 
/*     */   public static Field getFieldByFieldName(Object obj, String fieldName) {
/* 118 */     if ((obj == null) || (fieldName == null)) {
/* 119 */       return null;
/*     */     }
/* 121 */     for (Class superClass = obj.getClass(); superClass != Object.class; )
/*     */     {
/*     */       try {
/* 124 */         return superClass.getDeclaredField(fieldName);
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/* 122 */       superClass = superClass
/* 122 */         .getSuperclass();
/*     */     }
/*     */ 
/* 128 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.PluginUtils
 * JD-Core Version:    0.6.0
 */