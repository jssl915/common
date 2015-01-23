# common
  该项目为多套后台框架

  总体介绍： 
  
1 数据库支持mysql和oracle两种

2 后台框架前端UI支持operamasks和easyui

3 代码生成器支持mysql和oracle 

4 另有一套支持换肤的系统，提供operamasks的多套皮肤，感觉并不好，已废弃。

这套后台权限控制系统主要用的springMVC+mybatis实现，采用Spring整合RMI实现客户端与服务端分离，

其中用到缓存Oscache，目前主要对数据字典进行缓存。另外分页同用的mybatis自带的分页插件处理的,

由于mybatis自带的分页插件是在内存中进行分页，即先查出所有的数据再进行分页，这里进行扩展，

oracle和mysql扩展的分页方法有所不同。

-------------------------------------------------------------------------------------------------

服务端，即接口项目介绍:

com_service是操作的oracle数据库，可以对com_operamasks_system和com_easyui_system两套系统提供服务。

com_mysql_service是操作mysql数据库的，也可以对com_operamasks_system和com_easyui_system两套系统提供服务。

上面还有一套命名为com_mysql_system，这套系统采用的是operamasks前台UI框架，和com_operamasks_system基本一样，

就是远程调用服务端的端口配置不一样而已。

服务端com_service和com_mysql_service两者提供的接口基本一样。不同之处主要有两点:

1 insert方法不同，oracle主要靠序列来实现自增长，mysql建表时就把该字段（主键）设为自增长。

2 分页查询方式不同，oracle主要在外面嵌套一层，mysql直接limit start,end实现。

-------------------------------------------------------------------------------------------------

代码生成器介绍:

mybatis-generator 和 mysql-mybatis-generator 是两套代码生成器。

mybatis-generator仅支持oracle数据库代码生成,mysql-mybatis-generator支持oracle和mysql两种数据库。

这两个代码生成工具主要生成实体类entity,mapper以及service接口。

在这里由于本人对代码进行扩展，采用了继承和泛型，将mapper 和 service 生成的公共的8个接口写到父类。 

故生成的mapper.java和service.java里面接口为空。但都支持的八个方法分别如下：

public abstract void insert(T paramT); //新增
	
public void deleteByCondition(Map<String, Object> condition);//按条件删除
	
public void updateByCondition(T record);//按条件修改
	
public  List<T> selectByCondition(Map<String,Object> condititon);//按条件查询
	
public Integer countByCondition(Map<String, Object> condititon);//按条件查询总数
	
public abstract List<T> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds);//按条件查询，支持分页
	
public void deleteByPrimaryKey(Long id); //根据主键删除

public T findByPrimaryKey(Long id); //根据主键查询

这八个方法对于单表来说，基本上实现了所有接口，如果需要查询多表或操作多表，需要自己定义接口，实现方法。

当然如果对上面八个接口需要扩展，可以进行重写。

-------------------------------------------------------------------------------------------------

客户端，即前端项目介绍:

前端UI组件主要用到了operamasks 和 easyui,这两套组件提供了大部分UI，如Tree,Grid,Tab,Layout,Tip,validate,upload....

弹出层采用的jquery.artDialog,自己封装了add edit delete 以前搜索框里面的查询和清空方法等。

实现了框架左边的菜单树。

定义了表单的样式，按钮的样式等。

扩展了一个图片上传，拖拽排序的组件。

图表组件用的是highcharts-4.0.3

-------------------------------------------------------------------------------------------------



