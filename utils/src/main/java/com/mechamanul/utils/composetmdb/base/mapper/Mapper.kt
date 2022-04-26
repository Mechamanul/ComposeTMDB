package com.mechamanul.utils.composetmdb.base.mapper

interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

}