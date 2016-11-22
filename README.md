# Projeto de Teste para Multiplas Unidades de Persistência com Transações JTA/JPA

## Main Test

```java
try {

	// BEGIN ALL
	userTransaction.begin();

	// PU News
	newsCategoryDao.create(category);
	newsDao.create(news);

	// PU User
	userDao.create(user);

	// COMMIT ALL
	userTransaction.commit();

} catch (Exception e) {

	// ROLLBACK ALL!	
	userTransaction.rollback();

}
```

Link to complete code: https://github.com/juliancesar/demoiselle-multipersistenceunit/blob/master/src/main/java/br/com/juliancesar/multipersistenceunit/business/StartBusiness.java


## Test

* POST http://localhost:8080/multipersistenceunit/api/start/false/false/false/

## Standalone.xml

```xml
<xa-datasource jndi-name="java:jboss/datasources/UserDS" pool-name="UserDS" enabled="true" use-java-context="true">
    <xa-datasource-property name="ServerName">
        localhost
    </xa-datasource-property>
    <xa-datasource-property name="DatabaseName">
        pu_user
    </xa-datasource-property>
    <driver>com.mysql</driver>
    <security>
        <user-name>root</user-name>
        <password>root</password>
    </security>
    <validation>
        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
        <validate-on-match>false</validate-on-match>
        <background-validation>false</background-validation>
        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
    </validation>
    <statement>
        <share-prepared-statements>false</share-prepared-statements>
    </statement>
</xa-datasource>
<xa-datasource jndi-name="java:jboss/datasources/NewsDS" pool-name="NewsDS" enabled="true" use-java-context="true">
    <xa-datasource-property name="ServerName">
        localhost
    </xa-datasource-property>
    <xa-datasource-property name="DatabaseName">
        pu_news
    </xa-datasource-property>
    <driver>com.mysql</driver>
    <security>
        <user-name>root</user-name>
        <password>root</password>
    </security>
    <validation>
        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
        <validate-on-match>false</validate-on-match>
        <background-validation>false</background-validation>
        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
    </validation>
    <statement>
        <share-prepared-statements>false</share-prepared-statements>
    </statement>
</xa-datasource>

<drivers>
    <driver name="com.mysql" module="com.mysql">
        <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
    </driver>
</drivers>

```