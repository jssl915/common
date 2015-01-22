# common
  该项目为后台框架
1 数据库支持mysql和oracle
2 前端UI支持operamasks和easyui
3 代码生成器支持mysql和oracle
4 另有一套支持换肤的系统，提供operamasks的两套皮肤
其中支持oracle的service对于两套前端ui可以共用。
支持mysql的service对于两套前端ui也可以共用。

mysql数据库和oracle数据库主要有两处不同:
1 用到的ibatis的分页处理过程中，oracle拦截拼成分页主要在外面包一层，用rownum来分页，
  mysql主要用limit start,end来分页。
2 生成的insert方法不同，oracle主要是靠序列来实现自增长,mysql直接主主键自增长。

代码生成器可以生成实体类entity,数据库操层mapper以及service接口。其中用到继承和泛型，将公用的8个接口写到父类。
故生成的mapper.java和service.java里面接口为空。但支持的八个方法分别如下：
public abstract void insert(T paramT); //新增
	
public void deleteByCondition(Map<String, Object> condition);//按条件删除
	
public void updateByCondition(T record);//按条件修改
	
public  List<T> selectByCondition(Map<String,Object> condititon);//按条件查询
	
public Integer countByCondition(Map<String, Object> condititon);//按条件查询总数
	
public abstract List<T> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds);//按条件查询，支持分页
	
public void deleteByPrimaryKey(Long id); //根据主键删除

public T findByPrimaryKey(Long id); //根据主键查询

前端UI组件主要用到了operamasks 和 easyui,这两套组件提供了大部分UI，如Tree,Grid,Tab,Layout,Tip,validate,upload....

弹出层都是用的jquery.artDialog,自己封装了add edit delete 以前搜索框里面的查询和清空方法等。

自己实现了后台框架左边的菜单树。

另外自己定义了表单的样式，按钮的样式等。

这套框架可以节省不少代码的时间。



