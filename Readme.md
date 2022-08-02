# Second-handCarAssessment

## 项目简介

大三安卓课程的课程项目，模仿蓝本价软件实现二手车估价软件，该项目的数据存放在本地的sqlite中。

## 功能简介

1. 使用sqlite实现了注册登录功能
2. 利用sharedpreference实现记住密码功能
3. 利用广播实现了强制下线的功能
4. 利用fragment + viewpager+ tablayout实现了二手车App的总体页面
5. 利用RecyclerView实现了汽车信息列表的展示和具体信息的展示
6. 实现了搜索汽车信息的功能
7. 利用ScrollView实现了，“操作手册”、“评估简介”和“据评规则”
8. 实现了“在线反馈”和“电话反馈”的功能
9. 通过sqlite实现了浏览记录和收藏记录的功能
10. 实现了“软件介绍”和“软件分享功能”。

## 功能详解

1、点击进入app，出现的界面如下图所示。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image002.jpg) ![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image004.jpg)

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image006.jpg) ![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image008.jpg)

2、在未登录的时候，无法使用浏览记录、收藏记录、退出登录功能。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image010.jpg) ![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image012.jpg)

3、然后使用登录功能，登录界面如下。且每个界面的账户栏会有相应的更新。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image014.jpg)![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image016.jpg)![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image018.jpg)                    

4、如果没有账户也可以进行注册，注册界面如下。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image022.jpg)

5、首先是“首页”可以看到有相应的车辆推荐

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image024.jpg)

6、然后是“专家评估”界面中，点击“操作手册”、“评估简介”，效果如下。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image026.jpg) ![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image028.jpg) ![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image030.jpg) 

7、点击“据评规则”，“在线反馈”和“电话反馈”的效果如下。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image032.jpg) ![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image034.jpg) ![d09b916303ab855cf83b93a4aa5f28b](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image036.jpg)

8、在“车源”界面中，搜索功能的使用效果如下，点击搜索后会出现相应的车辆。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image038.jpg)

9、车辆详细信息页面如下，点击车辆图片会出现保存图片的提示。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image040.jpg) ![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image042.jpg)

10、在详细信息界面内点击收藏按钮后，会有相应的提示。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image044.jpg) ![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image046.jpg)

11、“我的”的界面如下。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image048.jpg)

12、点击浏览记录的功能，可以看到最近浏览过的记录，点击清空会弹出确认窗口。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image050.jpg) ![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image052.jpg)

13、点击收藏记录的功能，可以看到最近收藏的记录。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image054.jpg)

14、点击“软件介绍”和“软件分享”，效果分别如下。

![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image056.jpg) ![img](file:///C:/Users/nibaba/AppData/Local/Temp/msohtmlclip1/01/clip_image058.jpg)