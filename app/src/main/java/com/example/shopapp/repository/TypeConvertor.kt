package com.example.shopapp.repository

import com.example.shopapp.repository.local.entity.FavoriteEntity
import com.example.shopapp.repository.local.entity.ProductEntity
import com.example.shopapp.repository.remote.models.ProductDto

object TypeConvertor {

    fun toFavoriteEntityFromProductDto(productDto: ProductDto): FavoriteEntity =
        FavoriteEntity(
            product_id = productDto.prod_id,
            product = toProductEntityFromProductDto(productDto)
        )

    fun toFavoriteEntityFromProductEntity(productEntity: ProductEntity): FavoriteEntity =
        FavoriteEntity(
            product_id = productEntity.prod_id,
            product = ProductEntity(
                prod_id = productEntity.prod_id,
                name = productEntity.name,
                description = productEntity.description,
                price = productEntity.price,
                stock = productEntity.stock,
                category = productEntity.category,
                brand = productEntity.brand,
                shell_type = productEntity.shell_type,
                top_deck = productEntity.top_deck,
                top_material = productEntity.top_material,
                back_deck = productEntity.back_deck,
                neck_material = productEntity.neck_material,
                overlay = productEntity.overlay,
                strings = productEntity.strings,
                neck_attachment = productEntity.neck_attachment,
                mensura = productEntity.mensura,
                neck_width = productEntity.neck_width,
                color = productEntity.color,
                tailpiece = productEntity.tailpiece,
                produced = productEntity.produced,
                cutout = productEntity.cutout,
                varnish = productEntity.varnish,
                form = productEntity.form,
                specials = productEntity.specials,
                lads = productEntity.lads,
                link = productEntity.link
            )
        )

    fun toProductEntityFromProductDto(productDto: ProductDto): ProductEntity =
        ProductEntity(
            prod_id = productDto.prod_id,
            name = productDto.name,
            description = productDto.description,
            price = productDto.price,
            stock = productDto.stock,
            category = productDto.category,
            brand = productDto.brand,
            shell_type = productDto.shell_type,
            top_deck = productDto.top_deck,
            top_material = productDto.top_material,
            back_deck = productDto.back_deck,
            neck_material = productDto.neck_material,
            overlay = productDto.overlay,
            strings = productDto.strings,
            neck_attachment = productDto.neck_attachment,
            mensura = productDto.mensura,
            neck_width = productDto.neck_width,
            color = productDto.color,
            tailpiece = productDto.tailpiece,
            produced = productDto.produced,
            cutout = productDto.cutout,
            varnish = productDto.varnish,
            form = productDto.form,
            specials = productDto.specials,
            lads = productDto.lads,
            link = productDto.link
        )



    val initialProduct = ProductEntity(
        prod_id = 0,
        name = "",
        description = "",
        price = "",
        stock = 0,
        category = "",
        brand = "",
        shell_type = "",
        top_deck = "",
        top_material = "",
        back_deck = "",
        neck_material = "",
        overlay = "",
        strings = "",
        neck_attachment = "",
        mensura = "",
        neck_width = "",
        color = "",
        tailpiece = "",
        produced = "",
        cutout = "",
        varnish = "",
        form = "",
        specials = "",
        lads = "",
        link = "",
    )

    val initialProductDto = ProductDto(
        prod_id = 0,
        name = "",
        description = "",
        price = "",
        stock = 0,
        category = "",
        brand = "",
        shell_type = "",
        top_deck = "",
        top_material = "",
        back_deck = "",
        neck_material = "",
        overlay = "",
        strings = "",
        neck_attachment = "",
        mensura = "",
        neck_width = "",
        color = "",
        tailpiece = "",
        produced = "",
        cutout = "",
        varnish = "",
        form = "",
        specials = "",
        lads = "",
        link = "",
    )
}