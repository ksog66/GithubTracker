package com.notchdev.githubtracker.data.remote.dto


import com.notchdev.githubtracker.domain.model.BranchDetail

class BranchResponse : ArrayList<BranchResponseItem>() {

    fun toBranchDetailList(): List<BranchDetail> {
        return this.map { it.toBranchDetail() }
    }
}