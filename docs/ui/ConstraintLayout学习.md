链接： https://blog.csdn.net/guolin_blog/article/details/53122387

1. Hori/VertBias
* 弹簧左右的**力量**设置
2. match_constraint(0dp)
* 大小的测量由约束决定
```
Important: MATCH_PARENT is not supported for widgets contained in a ConstraintLayout, though similar behavior can be defined by using MATCH_CONSTRAINT with the corresponding left/right or top/bottom constraints being set to "parent".
```

3. 在0dp的情况下，可以设置constraintDimensionRatio来**固定宽高比**。
4. constraintHorizontal_weight来调整在父布局的权重
5. constraintHorizontal_chainStyle可以用来调整链的分布方式.
* spread
* packed
* spread_inside
![f1cb61ccbe495d38087610f2ec7b9d88.jpeg](evernotecid://05ED8BDB-2D43-4ED2-98CD-051238493610/appyinxiangcom/23967696/ENResource/p1)

6. constrainCircle，确定view的重点当作圆心然后布局
7. 虚拟视图（不做绘制的视图，计算过程也是setMeasureDimension(0,0)）
* GuideLine辅助线
* Barrier
* Group（ref id加一堆，可以同时控制隐藏之类的）
8. app:layout_constrainedWidth=”true|false”是否是align效果
9. layout_constraintWidth_percent
![e9798fb85416ceb68f2bcb50d147a22a.jpeg](evernotecid://05ED8BDB-2D43-4ED2-98CD-051238493610/appyinxiangcom/23967696/ENResource/p3)




