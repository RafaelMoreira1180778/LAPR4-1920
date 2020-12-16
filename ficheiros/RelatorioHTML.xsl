<?xml version="1.0"?>
<lpr:stylesheet version="1.0" xmlns:lpr="http://www.w3.org/1999/XSL/Transform" >
    <lpr:template match="/">
        <html>
            <head>
                <style>
                    table,
                    th,
                    td {
                    border: 1px solid black;
                    border-collapse: collapse;
                    }
                    th,
                    td {
                    padding: 15px;
                    }
                </style>
            </head>
            <body>
                <img>
                    <lpr:attribute name="src">
                        <lpr:value-of select="relatório/páginaRosto/logotipoDEI"/>
                    </lpr:attribute>
                </img>
                <h1>Relatório
                    <lpr:value-of select="relatório/páginaRosto/tema"/>
                </h1>
                <h2>Data:
                    <lpr:value-of select="relatório/páginaRosto/data"/>
                </h2>
                <h2>Disciplina</h2>
                <ul>
                    <li>
                        <b>Designação:</b>
                        <lpr:value-of select="relatório/páginaRosto/disciplina/designação"/>
                    </li>
                    <li>
                        <b>Ano Curricular:</b>
                        <lpr:value-of select="relatório/páginaRosto/disciplina/anoCurricular"/>
                    </li>
                    <li>
                        <b>Sigla:</b>
                        <lpr:value-of select="relatório/páginaRosto/disciplina/sigla"/>
                    </li>
                </ul>

                <table style="width:100%">
                    <tr>
                        <th>Nome</th>
                        <th>Número</th>
                        <th>Email</th>
                    </tr>
                    <lpr:for-each select="relatório/páginaRosto/autor">
                        <tr>
                            <td>
                                <lpr:value-of select="nome"/>
                            </td>
                            <td>
                                <lpr:value-of select="número"/>
                            </td>
                            <td>
                                <lpr:value-of select="mail"/>
                            </td>
                        </tr>
                    </lpr:for-each>
                </table>
                <h2>Turma:
                    <lpr:value-of select="relatório/páginaRosto/turma"/>
                </h2>
                <h3>Professores</h3>
                <table>
                    <tr>
                        <th>Sigla</th>
                        <th>Tipo Aula</th>
                    </tr>
                    <lpr:for-each select="relatório/páginaRosto/professor">
                        <tr>
                            <td>
                                <lpr:value-of select="@sigla"/>
                            </td>
                            <td>
                                <lpr:value-of select="@tipoAula"/>
                            </td>
                        </tr>
                    </lpr:for-each>
                </table>
                <h2>
                    <lpr:value-of select="relatório/corpo/introdução/@tituloSecção"/>
                </h2>
                <p>
                    <lpr:value-of select="relatório/corpo/introdução/subsecção"/>
                </p>
                <lpr:for-each select="relatório/corpo/introdução/parágrafo">
                    <p>
                        <lpr:value-of select="text()"/>
                    </p>
                </lpr:for-each>

                <h2>
                    <lpr:value-of select="relatório/corpo/outrasSecções/análise/@tituloSecção"/>
                </h2>
                <lpr:for-each select="relatório/corpo/outrasSecções/análise/subsecção/parágrafo">
                    <p>
                        <lpr:value-of select="text()"/>
                    </p>
                </lpr:for-each>

                <h2>
                    <lpr:value-of select="relatório/corpo/outrasSecções/linguagem/@tituloSecção"/>
                </h2>
                <lpr:for-each select="relatório/corpo/outrasSecções/linguagem/subsecção/parágrafo">
                    <p>
                        <lpr:value-of select="text()"/>
                    </p>
                </lpr:for-each>

                <h3>Items</h3>
                <lpr:for-each select="relatório/corpo/outrasSecções/linguagem/listaItems/item">
                    <h4>
                        <lpr:value-of select="@label"/>
                    </h4>
                    <p>
                        <lpr:value-of select="text()"/>
                    </p>
                </lpr:for-each>

                <h2>
                    <lpr:value-of select="relatório/corpo/outrasSecções/transformações/@tituloSecção"/>
                </h2>
                <lpr:for-each select="relatório/corpo/outrasSecções/transformações/subsecção/parágrafo">
                    <p>
                        <lpr:value-of select="text()"/>
                    </p>
                </lpr:for-each>

                <h3>Items</h3>
                <lpr:for-each select="relatório/corpo/outrasSecções/transformações/listaItems/item">
                    <h4>
                        <lpr:value-of select="@label"/>
                    </h4>
                    <p>
                        <lpr:value-of select="text()"/>
                    </p>
                </lpr:for-each>

                <h2>
                    <lpr:value-of select="relatório/corpo/conclusão/@tituloSecção"/>
                </h2>
                <lpr:for-each select="relatório/corpo/conclusão/subsecção/parágrafo">
                    <p>
                        <lpr:value-of select="text()"/>
                    </p>
                </lpr:for-each>

                <h2>
                    <lpr:value-of select="relatório/corpo/referências/@tituloSecção"/>
                </h2>
                <lpr:for-each select="relatório/corpo/referências/refWeb">
                    <p>consultadoEm:<lpr:value-of select="consultadoEm"/>
                    </p>
                    <lpr:element name="a">
                        <lpr:attribute name="href">
                            <lpr:value-of select="URL"/>
                        </lpr:attribute>
                        <lpr:value-of select="descrição"/>
                    </lpr:element>
                </lpr:for-each>

                <h2>
                    <lpr:value-of select="relatório/anexos/@tituloSecção"/>
                </h2>

                <lpr:for-each select="relatório/anexos/listaItems/item">
                    <h4>
                        <lpr:value-of select="@label"/>
                    </h4>
                    <p>
                        <lpr:value-of select="text()"/>
                    </p>
                </lpr:for-each>
            </body>
        </html>
    </lpr:template>
</lpr:stylesheet>
