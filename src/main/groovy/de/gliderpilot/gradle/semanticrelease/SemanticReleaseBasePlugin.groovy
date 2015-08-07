/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.gliderpilot.gradle.semanticrelease

import org.ajoberstar.gradle.git.release.base.BaseReleasePlugin
import org.ajoberstar.gradle.git.release.base.ReleasePluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class SemanticReleaseBasePlugin implements Plugin<Project> {

    void apply(Project project) {
        project.with {
            plugins.apply(BaseReleasePlugin)
            SemanticReleasePluginExtension semanticRelease = extensions.create("semanticRelease", SemanticReleasePluginExtension, project)
            ReleasePluginExtension releaseExtension = project.extensions.findByType(ReleasePluginExtension)
            releaseExtension.with {
                grgit = semanticRelease.grgit
            }
        }
    }
}