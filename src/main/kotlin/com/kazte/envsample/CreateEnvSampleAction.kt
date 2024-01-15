package com.kazte.envsample

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import java.io.File
import java.io.FileWriter

class CreateEnvSampleAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project: Project? = event.project

        val envFile = File(project!!.basePath, ".env")
        val envSampleFile = File(project.basePath, ".env_sample")

        if (envSampleFile.exists()) {
            envSampleFile.delete()
        }

        if (envFile.exists()) {
            // Create .env_sample based on .env
            envSampleFile.createNewFile()
            envFile.forEachLine { line ->

                val l = line.trim()

                if (l.startsWith('#')) {
                    FileWriter(envSampleFile, true).use { writer ->
                        writer.appendln(l)
                    }
                } else if (l.isBlank()) {
                    FileWriter(envSampleFile, true).use { writer ->
                        writer.appendln(l)
                    }
                } else {
                    val key = line.split("=").firstOrNull()?.trim() + "="

                    key?.let {
                        FileWriter(envSampleFile, true).use { writer ->
                            writer.appendln(it)
                        }
                    }
                }
            }
        }
    }
}