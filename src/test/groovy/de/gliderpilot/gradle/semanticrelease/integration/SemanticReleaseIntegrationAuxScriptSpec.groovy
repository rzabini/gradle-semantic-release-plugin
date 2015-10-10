package de.gliderpilot.gradle.semanticrelease.integration

import de.gliderpilot.gradle.semanticrelease.SemanticReleasePluginIntegrationSpec
import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult
import spock.lang.Specification

class SemanticReleaseIntegrationAuxScriptSpec extends SemanticReleasePluginIntegrationSpec {

    @Override
    void setupGradleProject(){
        buildFile << '''
            apply from:'release.gradle'
            println version
        '''
        setupGitignore()

        file('release.gradle') << """
            buildscript{

                repositories{
                    jcenter()
                }

                dependencies{
                    classpath files('${getPluginCompileDir()}')
                    classpath files('${getPluginCompileDir().replace('/classes/','/resources/')}')
                    classpath "org.ajoberstar:gradle-git:1.3.0"
                    classpath 'com.jcabi:jcabi-github:0.23'
                }

            }

            apply plugin: de.gliderpilot.gradle.semanticrelease.SemanticReleasePlugin

"""
        runTasksSuccessfully(':wrapper')
    }
}
