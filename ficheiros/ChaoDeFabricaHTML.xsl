<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <htlm>
            <link rel="stylesheet" href="Style.css"></link>
            <body>
                <h1>Chao de Fabrica</h1>
                <h2>Materias Primas</h2>
                <table border="1">
                    <tr bgcolor="#55F9E1">
                        <th>ID</th>
                        <th>Descricao</th>
                    </tr>
                    <xsl:apply-templates select="ChaoDeFabrica/MateriasPrimas/MateriaPrima"/>
                </table>
                <h2>Categorias Materias Primas</h2>
                <table border="1">
                    <tr bgcolor="#55F9E1">
                        <th>ID</th>
                    </tr>
                    <xsl:apply-templates select="ChaoDeFabrica/CategoriasMateriasPrimas/CategoriaMateriasPrimas"/>
                </table>
                <h2>Produtos</h2>
                <table border="1">
                    <tr bgcolor="#55F9E1">
                        <th>ID</th>
                        <th>IDComercial</th>
                        <th>DescricaoBreve</th>
                        <th>DescricaoCompleta</th>
                        <th>Unidade</th>
                        <th>IDCategoria</th>
                    </tr>
                    <xsl:apply-templates select="ChaoDeFabrica/Produtos/Produto"/>
                </table>
                <h2>Linhas Producao</h2>
                <table border="1">
                    <tr bgcolor="#55F9E1">
                        <th>ID</th>
                    </tr>
                    <xsl:apply-templates select="ChaoDeFabrica/LinhasProducao/LinhaProducao"/>
                </table>
                <h2>Encomendas</h2>
                <table border="1">
                    <tr bgcolor="#55F9E1">
                        <th>ID</th>
                        <th>OrdemProducao</th>
                    </tr>
                    <xsl:apply-templates select="ChaoDeFabrica/Encomendas/Encomenda"/>
                </table>
                <h2>Maquinas</h2>
                <table border="1">
                    <tr bgcolor="#55F9E1">
                        <th>ID</th>
                        <th>NumeroSerie</th>
                        <th>Fabricante</th>
                        <th>Modelo</th>
                        <th>AnoFabrico</th>
                        <th>Descricao</th>
                        <th>DataIntalacao</th>
                        <th>HoraInstalacao</th>
                    </tr>
                    <xsl:apply-templates select="ChaoDeFabrica/Maquinas/Maquina"/>
                </table>
                <h2>Depositos</h2>
                <table border="1">
                    <tr bgcolor="#55F9E1">
                        <th>ID</th>
                        <th>Descricao</th>
                    </tr>
                    <xsl:apply-templates select="ChaoDeFabrica/Depositos/Deposito"/>
                </table>
                <h2>Ordens Producao</h2>
                <table border="1">
                    <tr bgcolor="#55F9E1">
                        <th>ID</th>
                        <th>Produto id</th>
                        <th>DataEmissao</th>
                        <th>DataPrevisao</th>
                        <th>Encomendas</th>
                        <th>Quantidade</th>
                        <th>UnidadeMedida</th>
                    </tr>
                    <xsl:apply-templates select="ChaoDeFabrica/OrdensProducao/OrdemProducao"/>
                </table>
            </body>
        </htlm>
    </xsl:template>

    <xsl:template match="MateriaPrima">
        <tr>
            <td>
                <xsl:value-of select="@id"/>
            </td>
            <td>
                <xsl:value-of select="Descricao"/>
            </td>
        </tr>
    </xsl:template>

    <xsl:template match="CategoriaMateriasPrimas">
        <tr>
            <td>
                <xsl:value-of select="@id"/>
            </td>
        </tr>
    </xsl:template>

    <xsl:template match="Produto">
        <tr>
            <td>
                <xsl:value-of select="@id"/>
            </td>
            <td>
                <xsl:value-of select="IDComercial"/>
            </td>
            <td>
                <xsl:value-of select="DescricaoBreve"/>
            </td>
            <td>
                <xsl:value-of select="DescricaoCompleta"/>
            </td>
            <td>
                <xsl:value-of select="Unidade"/>
            </td>
            <td>
                <xsl:value-of select="IDCategoria"/>
            </td>
        </tr>
    </xsl:template>

    <xsl:template match="LinhaProducao">
        <tr>
            <td>
                <xsl:value-of select="@id"/>
            </td>
        </tr>
    </xsl:template>

    <xsl:template match="Encomenda">
        <tr>
            <td>
                <xsl:value-of select="@id"/>
            </td>
            <td>
                <xsl:value-of select="OrdemProducao/@id"/>
            </td>
        </tr>
    </xsl:template>

    <xsl:template match="Maquina">
        <tr>
            <td>
                <xsl:value-of select="@id"/>
            </td>
            <td>
                <xsl:value-of select="numeroSerie"/>
            </td>
            <td>
                <xsl:value-of select="Fabricante"/>
            </td>
            <td>
                <xsl:value-of select="Modelo"/>
            </td>
            <td>
                <xsl:value-of select="AnoFabrico"/>
            </td>
            <td>
                <xsl:value-of select="Descricao"/>
            </td>
            <td>
                <xsl:value-of select="Instalacao/Data"/>
            </td>
            <td>
                <xsl:value-of select="Instalacao/Hora"/>
            </td>
        </tr>
    </xsl:template>

    <xsl:template match="Deposito">
        <tr>
            <td>
                <xsl:value-of select="@id"/>
            </td>
            <td>
                <xsl:value-of select="Descricao"/>
            </td>
        </tr>
    </xsl:template>

    <xsl:template match="OrdemProducao">
        <tr>
            <td>
                <xsl:value-of select="@id"/>
            </td>
            <td>
                <xsl:value-of select="Produto/@id"/>
            </td>
            <td>
                <xsl:value-of select="DataEmissao"/>
            </td>
            <td>
                <xsl:value-of select="DataPrevisao"/>
            </td>
            <td>
                <xsl:value-of select="encomendas"/>
            </td>
            <td>
                <xsl:value-of select="Quantidade"/>
            </td>
            <td>
                <xsl:value-of select="UnidadeMedida"/>
            </td>
        </tr>
    </xsl:template>

</xsl:stylesheet>