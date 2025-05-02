package com.nextlevel.subscription.application

import java.util.function.Function

interface Presenter<UC_OUT, NEW_OUT> : Function<UC_OUT, NEW_OUT>