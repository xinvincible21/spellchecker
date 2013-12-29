package org.spellchecker

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.Iterator
import java.util.List

import com.swabunga.spell.engine.SpellDictionary
import com.swabunga.spell.engine.SpellDictionaryHashMap
import com.swabunga.spell.event.SpellCheckEvent
import com.swabunga.spell.event.SpellCheckListener
import com.swabunga.spell.event.SpellChecker
import com.swabunga.spell.event.StringWordTokenizer

class Suggest {

  class SuggestionListener extends SpellCheckListener {

    def spellingError(event:SpellCheckEvent) {
      println("Misspelling: " + event.getInvalidWord())

      val suggestions = event.getSuggestions()
      if (suggestions.isEmpty()) {
        println("No suggestions found.")
      } else {
        print("Suggestions: ")

        val i = suggestions.iterator

        while(i.hasNext)
        {
          print(i.next())
          if (i.hasNext()) {
            print(", ")
          }
        }
        println()
      }
    }
  }

  def main(args: Array[String]) {
    if (args.length < 1) {
      println("usage: Suggest <dictionary file>")
      System.exit(1)
    }

    val f = new File(args(0))
    val dict = new SpellDictionaryHashMap(f)
    val spellChecker = new SpellChecker(dict)
    spellChecker.addSpellCheckListener(new SuggestionListener())

    val in = new BufferedReader(new InputStreamReader(System.in))
    while (true) {
      print("> ")
      val line = in.readLine()
      spellChecker.checkSpelling(new StringWordTokenizer(line))
      println()
    }
  }

}
