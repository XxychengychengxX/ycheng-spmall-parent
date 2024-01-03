package com.project.ychengspmall.model.entity.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "产品单元实体类")
public class ProductUnit extends BaseEntity {

	@Schema(description = "名称")
	private String name;

}