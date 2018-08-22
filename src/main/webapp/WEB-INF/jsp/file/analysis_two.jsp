<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page
	import="org.jfree.data.general.DefaultPieDataset,org.jfree.chart.ChartFactory
,org.jfree.chart.JFreeChart,org.jfree.chart.servlet.*,
org.jfree.data.category.DefaultCategoryDataset,org.jfree.chart.title.TextTitle
,org.jfree.chart.servlet.ServletUtilities,org.jfree.chart.renderer.category.BarRenderer,
org.jfree.chart.plot.PlotOrientation,org.jfree.chart.plot.CategoryPlot,org.jfree.chart.axis.ValueAxis,
org.jfree.chart.axis.CategoryAxis,org.jfree.chart.JFreeChart,org.jfree.chart.ChartColor,java.awt.Font"%>
<html>
<head>
</head>
<body>
	<%
		// 1. 获得数据集合
		// 获取一个演示用的组合数据集对象
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(40, "", "普通动物学");
		dataset.addValue(50, "", "生物学");
		dataset.addValue(60, "", "动物解剖学");
		dataset.addValue(70, "", "生物理论课");
		dataset.addValue(80, "", "动物理论课");
		// 2. 创建柱状图
		JFreeChart chart = ChartFactory.createBarChart3D("学生对教师授课满意度", // 图表标题
				"课程名", // 目录轴的显示标签
				"百分比", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				false, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
				);
		// 3. 设置整个柱状图的颜色和文字（char对象的设置是针对整个图形的设置）
		chart.setBackgroundPaint(ChartColor.WHITE); // 设置总的背景颜色

		// 4. 获得图形对象，并通过此对象对图形的颜色文字进行设置
		CategoryPlot p = chart.getCategoryPlot();// 获得图表对象
		p.setBackgroundPaint(ChartColor.lightGray);// 图形背景颜色
		p.setRangeGridlinePaint(ChartColor.WHITE);// 图形表格颜色

		// 5. 设置柱子宽度
		BarRenderer renderer = (BarRenderer) p.getRenderer();
		renderer.setMaximumBarWidth(0.06);

		// 解决乱码问题
		// 设置文字样式
		// 1. 图形标题文字设置
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.BOLD, 20));

		// 2. 图形X轴坐标文字的设置
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		CategoryAxis axis = plot.getDomainAxis();
		axis.setLabelFont(new Font("宋体", Font.BOLD, 22)); // 设置X轴坐标上标题的文字
		axis.setTickLabelFont(new Font("宋体", Font.BOLD, 15)); // 设置X轴坐标上的文字

		// 2. 图形Y轴坐标文字的设置
		ValueAxis valueAxis = plot.getRangeAxis();
		valueAxis.setLabelFont(new Font("宋体", Font.BOLD, 15)); // 设置Y轴坐标上标题的文字
		valueAxis.setTickLabelFont(new Font("sans-serif", Font.BOLD, 12));// 设置Y轴坐标上的文字

		// 6. 将图形转换为图片，传到前台
		String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400,
				null, request.getSession());
		String chartURL = request.getContextPath() + "/chart?filename="
				+ fileName;
	%>
	<img src="<%=chartURL%>" width="800" height="600">
</body>
</html>
