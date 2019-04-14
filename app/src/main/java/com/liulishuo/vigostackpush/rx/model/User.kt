package com.liulishuo.vigostackpush.rx.model

data class User(
        val name: String,
        val addrs: List<Addr>
)

data class Addr(
        val street: String
)
