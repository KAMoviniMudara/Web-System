package com.example.Web.System.util.mappers;

import com.example.Web.System.dto.IssueTitleDTO;
import com.example.Web.System.entity.IssueTitle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IssueTitleMapper {

    IssueTitleMapper INSTANCE = Mappers.getMapper(IssueTitleMapper.class);

    @Mapping(source = "categories", target = "categories") // Map the categoryId
    IssueTitleDTO toDto(IssueTitle entity);

    IssueTitle toEntity(IssueTitleDTO dto);
}
