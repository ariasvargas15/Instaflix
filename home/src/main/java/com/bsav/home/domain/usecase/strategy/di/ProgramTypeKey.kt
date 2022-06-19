package com.bsav.home.domain.usecase.strategy.di

import com.bsav.home.domain.model.ProgramType
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ProgramTypeKey(val value: KClass<out ProgramType>)
