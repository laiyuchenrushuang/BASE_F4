# BASE_F4
有时对四大组件还不熟悉，这个是用来巩固基础的，后续持续更新----菜逼的自学

# 版本修改记录

2019.05.28  权限广播注意 permission的名字 不要加特殊符号，除了“.”可以，不然权限无法接收的问题。  权限的广播注意Category这个属性，intent和静态的XML里面receiver注册的时候要保持一致，不然也接收不到。总结就这两点，弄了几天。

ContentProvider就不写了，之前有个demo，实质数据共享Binder机制。（https://github.com/laiyuchenrushuang/ContentProviderListener）
