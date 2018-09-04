# 河北金融学院学科竞赛管理系统

---
## 项目的一些琐事
大二开学时，校团委委托我编写了该系统（当时作为一个刚刚接触java的大二学生竟然接下了这个任务）。这个系统成为了自己第一个完整的web项目。该系统的开发大大简化了校团委的学科竞赛认定工作，提高了一些计算的准确度，在校团委科创工作一年的我深有体会。

在指导老师（JZW）一个肥宅（HZF）和一个超漂亮的学姐（ZXX）的帮助下完成开发。系统使用springMVC与Hibernate，使用MySQL数据库，前端设计感觉还算漂亮（相比学校正在使用的系统自我感觉良好）。基本实现了日常工作的大部分功能，比如第二课堂学分计算，教师工作量计算，还有一些统计查询功能。系统功能严格按照学校文件编写，不过实际认定还要看校团委的认定结果，会有部分出入。

由于实际原因该系统可能并没有实际接入校园网，只在一台主机上运行，供校团委内部使用即可，一方面是编写质量问题，不足以达到《网络安全法》要求，毕竟是我的第一个web项目，欠缺的东西很多，很多的地方有待改进。另一方面，团委的认定有时候比较随性，它不严格按学校规定我也很无奈（美赛就是最典型的例子），其次由于资金限制，没人能支持它的运行维护，团委没有相关经费，所以造成了现在的这种情况。这个项目如有需要后期会继续维护，以供正常的使用。

---
## 使用说明
 1. 学科竞赛总负责教师“新建”竞赛，并将“竞赛编号”通知各院系学科竞赛负 责人。
 2. 各院系竞赛负责人按 Excel模板填写竞赛名称、竞赛编号和队伍编号基本信息，再下发到各班级，填写参赛信息完毕后，经检查无误，可使用本院系注册后的用 户身份登录系统并上传参赛信息。
 3. 学科竞赛总负责教师在系统首页可“删除”所选竞赛，也可选择“导出管理” 进入到所选竞赛的导出界面。
 4. 总负责教师在“竞赛管理”页面选择相应的竞赛可查看该竞赛的基本信息、参赛人数、参赛队伍数和指导教师数，还可查看相应参赛队伍情况、参赛学生和指导教师情况。选择“修改竞赛信息”，可对相应竞赛进行更改操作。 
 5. 总负责教师选择“竞赛查询”，在输入竞赛名称后即可查询竞赛信息。
 6. 总负责教师选择“评判标准”和“奖金比例”，可分别查看或修改该信息。
 7. 选择“学生管理”，总负责教师可查看所有参赛学生的参赛情况，包括参赛队伍数、总学分和总奖金数。选择具体学生，可查看该学生每个队伍的获奖情况、个人 所获学分和奖金情况。
 8. 选择“教师管理”，总负责教师可查看所有指导老师的指导情况，包括指导队伍数和所获总工作量。选择具体教师，可查看该教师指导的所有队伍情况和相应个人所获工作量。
 9. 选择“导出管理”，总负责教师可在该页面选择导出某个竞赛的某个表。在参赛信息已导入的情况下，可导出的表包括学生奖金表、教师工作表和队伍奖金表。 
 10. 各院系负责人仅能上传参赛信息、查看本系某竞赛的参赛情况和导出 Excel。
