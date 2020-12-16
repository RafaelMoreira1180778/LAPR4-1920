<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" omit-xml-declaration="yes" indent="no"/>


    <xsl:template match="/">
        <xsl:apply-templates select="ChaoDeFabrica/MateriasPrimas/MateriaPrima"/>

        <xsl:apply-templates select="ChaoDeFabrica/CategoriasMateriasPrimas/CategoriaMateriasPrimas"/>

        <xsl:apply-templates select="ChaoDeFabrica/Produtos/Produto"/>

        <xsl:apply-templates select="ChaoDeFabrica/LinhasProducao/LinhaProducao"/>

        <xsl:apply-templates select="ChaoDeFabrica/Encomendas/Encomenda"/>

        <xsl:apply-templates select="ChaoDeFabrica/Maquinas/Maquina"/>

        <xsl:apply-templates select="ChaoDeFabrica/Depositos/Deposito"/>

        <xsl:apply-templates select="ChaoDeFabrica/OrdensProducao/OrdemProducao"/>

    </xsl:template>

    <xsl:template match="MateriaPrima">
        <xsl:value-of select="concat('materia prima - ',@id, ', ', Descricao, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="CategoriaMateriasPrimas">
        <xsl:value-of select="concat('categoria materias primas - ',@id, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="Produto">
        <xsl:value-of select="concat('produto - ',@id, ', ', IDComercial, ', ', DescricaoBreve, ', ', DescricaoCompleta, ', ', Unidade,', ', IDCategoria, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="LinhaProducao">
        <xsl:value-of select="concat('linha de producao - ',@id, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="Encomenda">
        <xsl:value-of select="concat('encomenda - ',@id, ', ', OrdemProducao/@id, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="Maquina">
        <xsl:value-of select="concat('maquina - ',@id, ', ', numeroSerie, ', ', Fabricante, ', ', Modelo, ', ', AnoFabrico,', ', Descricao, ', ', Instalacao/Data, ', ', Instalacao/Hora, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="Deposito">
        <xsl:value-of select="concat('deposito - ',@id, ', ', Descricao, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="OrdemProducao">
        <xsl:value-of select="concat('ordem de producao - ',@id, ', ', Produto/@id, ', ', DataEmissao, ', ', DataPrevisao, ', ', encomendas,', ', Quantidade, ', ', UnidadeMedida, '&#xa;')"/>
    </xsl:template>

</xsl:stylesheet>


