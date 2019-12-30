# !/user/bin/env python
# coding:utf-8
# Author:wangyx

from pygls.features import COMPLETION

from pygls.server import LanguageServer

from pygls.types import CompletionItem, CompletionList, CompletionParams

server = LanguageServer()

@server.feature(COMPLETION, trigger_characters=[','])

def completions(params: CompletionParams):

    """Returns completion items."""

    return CompletionList(False, [

        CompletionItem('hello'),

        CompletionItem('world'),

        CompletionItem('test_pygls')

    ])

server.start_tcp('localhost', 8080)