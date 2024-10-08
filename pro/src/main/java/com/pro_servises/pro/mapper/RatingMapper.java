package com.pro_servises.pro.mapper;


import com.pro_servises.pro.dto.RatingDto;
import com.pro_servises.pro.model.Order;
import com.pro_servises.pro.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RatingMapper {



    RatingMapper INSTANCE = Mappers.getMapper( RatingMapper.class );

    RatingDto mapToRatingDto(Order order);

    Rating mapToRating(RatingDto ratingDto);

}
