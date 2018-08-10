package de.arraying.scale.command

import de.arraying.scale.command.commands.PingCommand

/**
  * Copyright 2018 Arraying
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
abstract class Command(val name: String, val aliases: Array[String]) {

  /**
    * Executes the command.
    * @param context The context.
    */
  def execute(context: Context)

}

/**
  * The companion object.
  */
object Command {

  /**
    * All commands
    */
  val commands: Set[Command] = Set(
    new PingCommand()
  )

}
