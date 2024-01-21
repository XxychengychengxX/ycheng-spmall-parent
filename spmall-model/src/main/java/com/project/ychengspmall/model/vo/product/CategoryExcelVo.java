package com.project.ychengspmall.model.vo.product;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@HeadRowHeight(20)
public class CategoryExcelVo {

	@ExcelProperty(value = "id" ,index = 0)
	@ColumnWidth(20)
	private Long id;

	@ExcelProperty(value = "名称" ,index = 1)
	@ColumnWidth(20)
	private String name;

	@ExcelProperty(value = "图片url" ,index = 2)
	@ColumnWidth(50)
	private String imageUrl ;

	@ExcelProperty(value = "上级id" ,index = 3)
	@ColumnWidth(20)
	private Long parentId;

	@ExcelProperty(value = "状态" ,index = 4)
	@ColumnWidth(20)
	private Integer status;

	@ExcelProperty(value = "排序" ,index = 5)
	@ColumnWidth(20)
	private Integer orderNum;

}